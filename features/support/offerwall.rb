def launch_offerwall
  press_button 'Launch Offer Wall' if main_activity?
end

def wait_for_offerwall
  if performAction('wait_for_screen', 'OfferWallActivity', '15')['success']
    wait_for(30.to_f) {
      sleep 1
      offerwall_visible?
    }
  end
end

def offerwall?
  performAction('get_activity_name')['message'] == 'OfferWallActivity'
end

def offerwall_visible?
  value = false
  value = query("webView css:'li'").size > 0 ||
    query("webView css:'.sp_header'").size > 0 ||
    query("webView css:'.page-policy '")if offerwall?
  value && !progress_dialog?
end

# def get_currency_header_text
#   (query("webView css:'.truncate-text'")[0]["html"].match /\>(.*)\</)[1]
# end

def get_offers_in_page
  raise "Offerwall is not displayed currently" unless offerwall_visible?
  query("webView css:'li'").size
end

def more_offers?
  raise "Offerwall is not displayed currently" unless offerwall_visible?
  query("webView css:div#more[style=\"display: none; \"]").size == 0
end

def touch_offer_with_lpid (lpid, expandMoreOffers=false)
  raise "Offer is not available" unless offer_with_lpid? lpid, expandMoreOffers
  touch_button_offerwall("li[data-lp=\"#{lpid}\"]")
end

def offer_with_lpid? (lpid, expandMoreOffers=false)
  raise "Invalid argument" unless lpid.is_a? Integer
  raise "Offerwall is not displayed currently" unless offerwall_visible?
  query_string = "webView css:'li[data-lp=\"#{lpid}\"]'"
  offer = query(query_string).size == 1
  until (offer || !expandMoreOffers || !more_offers?) do
    touch_more_offers
    offer = query(query_string).size == 1
    sleep 2
  end
  offer
end

def touch_offer (index)
  raise "Invalid argument provided, must be an Integer" unless index.is_a? Integer
  scroll_until("li:nth-of-type(#{index})")
  performAction('touch', "css","li:nth-of-type(#{index})") 
end

def touch_back_button
  #touch_button("#btn-back")
  # this way, also works from the all the web pages
  # privacy, support, help
  buttons = ["button left back", "button back left"]
  buttons.each do |b|
    css = "a[class=\"#{b}\"]"
    if query("webView css:'#{css}'").size > 0
      touch_button_offerwall css
      break
    end
  end
end

def touch_privacy_button
  touch_button_offerwall("#btn-priv")
end

def touch_support_button
  touch_button_offerwall("#btn-supp")
end

def touch_help_button
  touch_button_offerwall("#btn-help")
end

def touch_more_offers
  raise "No more offers available" unless more_offers?
  touch_button_offerwall("#more > input")
end

#helper methods
def touch_button_offerwall (button)
  touch_button (button) {check_offerwall}
end

