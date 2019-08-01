(ns backwoods-poetry.core
  (:require [backwoods-poetry.routes :as r]
            [compojure.handler :as handler]))

(def handler
  (handler/site r/routes))


