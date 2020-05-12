(ns mpark-newsletter.http
  (:require [clj-http.client :as client]))

(defn get-page-html
  []
  (let [res (client/get "http://mlbpark.donga.com/mp/b.php?b=kbotown")
        status-code (:status res)]
    ;; TODO check status code
    (:body res)))
