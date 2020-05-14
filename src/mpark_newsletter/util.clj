(ns mpark-newsletter.util
  (:import (java.time Instant ZoneOffset)
           (java.time.format DateTimeFormatter)
           (java.io File)))

(defn filter-attrs
  [elements class]
  (first (filter #(= {:class class} (:attrs %)) elements)))

(defn first-content
  [element]
  (first (:content element)))

(defn filter-string
  [coll]
  (filter #(not (string? %)) coll))

;;;;

(defn is-file? [^File f] (.isFile f))

;;;;

(def ^:dynamic *now-inst-fn* #(Instant/now))

(defn now-inst [] (*now-inst-fn*))

(def formatter-utc-'yyyysMMsdd'
  (-> (DateTimeFormatter/ofPattern "yyyy/MM/dd")
      (.withZone ZoneOffset/UTC)))

(def formatter-utc-'yyyyMMddHH'
  (-> (DateTimeFormatter/ofPattern "yyyyMMddHH")
      (.withZone ZoneOffset/UTC)))

(def formatter-utc-'yyyyMMdd'
  (-> (DateTimeFormatter/ofPattern "yyyyMMdd")
      (.withZone ZoneOffset/UTC)))

(defn date-'yyyysMMsdd' [inst] (.format formatter-utc-'yyyysMMsdd' inst))
(defn date-'yyyyMMddHH' [inst] (.format formatter-utc-'yyyyMMddHH' inst))
(defn date-'yyyyMMdd' [inst] (.format formatter-utc-'yyyyMMdd' inst))

(defn now-'yyyysMMsdd' [] (date-'yyyysMMsdd' (now-inst)))
(defn now-'yyyyMMddHH' [] (date-'yyyyMMddHH' (now-inst)))
(defn now-'yyyyMMdd' [] (date-'yyyyMMdd' (now-inst)))
