(ns mpark-newsletter.core
  (:gen-class)
  (:require [clojure.pprint :as pp]
            [clojure.java.io :as io]
            [mpark-newsletter.http :refer [get-page-html]]
            [mpark-newsletter.html :refer [parse aggregate-today-articles]]
            [mpark-newsletter.util :refer :all]))

(defn crawl
  []
  (binding [pp/*print-right-margin* 100]
    (let [dir-path (format "resources/articles/%s" (now-'yyyysMMsdd'))
          file-name (format "%s-articles.edn" (now-'yyyyMMddHH'))
          file-path (format "%s/%s" dir-path file-name)
          articles (-> (get-page-html) parse pp/pprint with-out-str)]
      (io/make-parents file-path)
      (spit file-path articles))))

(defn aggregate
  []
  (binding [pp/*print-right-margin* 100]
    (let [dir-path (format "resources/articles/%s" (now-'yyyysMMsdd'))
          file-name (format "%s-articles.edn" (now-'yyyyMMdd'))
          file-path (format "%s/%s" dir-path file-name)
          articles (-> (aggregate-today-articles dir-path) pp/pprint with-out-str)]
      (io/make-parents file-path)
      (spit file-path articles))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
