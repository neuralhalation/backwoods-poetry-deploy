(ns backwoods-poetry.random-haiku.view
  (:use [hiccup.page] [hiccup.element] [hiccup.form])
  (:require
   [backwoods-poetry.random-haiku.middleware :refer [line1 line2 line3 line-generator]]
   [backwoods-poetry.views :refer [footer]]))

(defn random-haiku
  [word-list]
  [:div {:id "content"}
   [:h1 {:class "text-success"} "shitty random haikus"]
   [:p {:class "haiku-line"} (line-generator (line1 word-list))]
   [:p {:class "haiku-line"} (line-generator (line2 word-list))]
   [:p {:class "haiku-line"} (line-generator (line3 word-list))]
   (link-to {:class "btn btn-primary"} "/random-haiku" "inspire me more")
   (footer)
   ])
