(defproject backwoods-poetry "0.1.0-SNAPSHOT"
  :description "Various poetry producing bots written in Clojure."
  :url "http://backwoods-research-poetry.herokuapp.com"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"],
                 [clj-http "3.10.0"],
                 [ring/ring-json "0.4.0"],
                 [ring/ring-core "1.7.1"],
                 [ring/ring-jetty-adapter "1.7.1"],
                 [ring/ring-devel "1.7.1"],
                 [hiccup "1.0.5"],
                 [compojure "1.5.1"],
                 [cheshire "5.8.1"]]
  :main ^:skip-aot backwoods-poetry.core
  :min-lein-version "2.0.0"
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :ring {:handler backwoods-poetry.core/handler}
  :plugins [[lein-ring "0.12.1"]]
  :uberjar-name "backwoods-poetry-standalone.jar")


