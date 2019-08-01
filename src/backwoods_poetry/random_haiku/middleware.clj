(ns backwoods-poetry.random-haiku.middleware
  (:require
   [clj-http.client :as http]
   [cheshire.core :refer :all]
   [ring.middleware.params :as p]))

(defn basic-count
  [word]
  (clojure.string/replace word #"(?:[^laeiouy]es|ed|[^laeiouy]e)$" ""))

(defn y?
  [word]
  (clojure.string/replace word #"^y" ""))

(defn match-syllables
  [word]
  (re-seq #"[aeiouy]{1,2}" word))

(defn match
  [word]
  (match-syllables (y? (basic-count word))))

(defn matches
  [words]
  (map match words))

(defn get-lengths
  [words]
  (map count (matches words)))

(defn pair
  [words]
  (zipmap words (get-lengths words)))

(defn word-by-syllable
  [length pairs]
  (filter (comp #{length} pairs) (keys pairs)))

(defn rand-syllable
  [max]
  (rand-int max))

(defn dif
  [max x]
  (- max x))

(def get-word-list
  (-> (http/get "https://api.noopschallenge.com/wordbot?set=default&count=100000") :body))

(defn decoder
  [word-list]
  (parse-string word-list))

(defn get-words
  [word-list]
  (get (decoder word-list) "words"))

(defn paired [word-list] (pair (get-words word-list)))

(defn get-word
  [length pairs]
  (let [xs [pairs]]
    (when (not (empty? xs))
      (rand-nth (word-by-syllable length pairs)))))


(defn short-line
  [max pairs]
  (let [x (rand-syllable max)]
    (if (== x max)
      [(get-word x pairs)]
      [(get-word x pairs) (get-word (dif max x) pairs)])))

(defn long-line
  [max pairs]
  (let [x (dif max 2) y (rand-syllable (dif max 2))]
    (if (== (+ x y) max)
      [(get-word x pairs) (get-word y pairs)]
      (if (< (+ x y) max)
        [(get-word x pairs) (get-word y pairs) (get-word (dif max (+ x y)) pairs)]
        [(get-word x pairs) (get-word y pairs) (get-word 1 pairs)]))))

(defn line1
  [word-list]
  (short-line 5 (paired word-list)))

(defn line2
  [word-list] 
  (long-line 7 (paired word-list)))

(defn line3
  [word-list] 
  (short-line 5 (paired word-list)))

(defn line-generator
  [line]
  (if line
    (clojure.string/join " " line)
    "fuck"))

(def wrap-params p/wrap-params)
