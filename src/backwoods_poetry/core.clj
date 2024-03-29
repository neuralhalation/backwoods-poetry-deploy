(ns backwoods-poetry.core
  (:gen-class)
  (:require [backwoods-poetry.routes :as r]
            [compojure.handler :as handler]
            [ring.adapter.jetty :as jetty]))

(defn parse-int [s]
  (Integer. (re-find #"\d+" s)))

(def handler
  (handler/site r/routes))

(defn -main []
  (let [port (parse-int (or (System/getenv "PORT") "777"))]
    (jetty/run-jetty handler {:port port :join? false})))

