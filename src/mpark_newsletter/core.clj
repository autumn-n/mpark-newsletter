(ns mpark-newsletter.core
  (:gen-class)
  (:require [clojure.pprint :as pp]
            [mpark-newsletter.http :refer [get-page-html]]
            [mpark-newsletter.html :refer [parse]]
            [mpark-newsletter.util :refer :all]
            [clojure.java.io :as io]))

(defn crawl-articles
  []
  (binding [pp/*print-right-margin* 100]
    (let [file-name (format "%s-articles.edn" (now-'yyyyMMddHH'))
          path-name (format "resources/articles/%s/%s" (now-'yyyysMMsdd') file-name)
          articles (-> (get-page-html) parse pp/pprint with-out-str)]
      (io/make-parents path-name)
      (spit path-name articles))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
