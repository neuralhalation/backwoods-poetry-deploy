(ns backwoods-poetry.routes
  (:require
   [compojure.core :refer [defroutes GET ANY]]
   [compojure.route :as route]
   [backwoods-poetry.views :as v]
   [backwoods-poetry.random-haiku.view :refer [random-haiku]]
   [backwoods-poetry.random-haiku.middleware :refer [get-word-list]]
   [backwoods-poetry.layout :refer [application]]))

(defroutes routes
  (GET "/" [] (application "Home" (v/main)))
  (GET "/random-haiku" [word-list] (application "random haikus" (random-haiku get-word-list)))
  (route/resources "/")
  (ANY "*" [] (route/not-found (application "Page Not Found" (v/not-found))))
)
