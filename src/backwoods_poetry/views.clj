(ns backwoods-poetry.views
  (:use [hiccup.page] [hiccup.element] [hiccup.form]))

(defn footer
  []
  [:div
   (link-to "/" "back home")])

(defn main
  []
  [:div {:id "content"}
   [:h1 {:class "text-success"} "Backwoods Research Group"]
   [:h2 "The Clojure Poetry Bots"]
   [:br]
   [:h5 (link-to "/random-haiku" "the random haiku bot")]
   [:p "A bot that generates random haikus pulling from " [:a {:href "http://noopschallenge.com/challenges/wordbot"} "noopschallenge.com"] " and using a regular expression to do syllable matching."]])

(defn not-found
  []
  [:div {:id "content"}
   [:h2 "ope"]
   [:p {:class "haiku-line"} "four-oh-four say we"]
   [:p {:class "haiku-line"} "the resource does not exist"]
   [:p {:class "haiku-line"} "try other flavors"]
   (link-to "/home" "back home")])

