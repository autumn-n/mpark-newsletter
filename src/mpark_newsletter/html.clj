(ns mpark-newsletter.html
  (:require [hickory.core :as h-c]
            [hickory.select :as h-s]
            [mpark-newsletter.util :refer :all]))

(defn parse-li
  [li]
  (let [author-html (filter-attrs li "info")
        article-html (filter-attrs li "title")]
    {:nick  (first-content (filter-attrs (:content author-html) "nick"))
     :url   (get-in (first-content article-html) [:attrs :href])
     :title (-> article-html first-content first-content)}))

(defn parse-lis
  [fence]
  (filter-string (:content fence)))

(defn parse-article
  [fence]
  (map (comp parse-li :content first-content)
       (parse-lis fence)))

(defn parse
  [page-html]
  (let [pc-kbotown-today (h-s/child (h-s/tag :div) (h-s/id "PC_kbotown_today"))
        fn-select (partial h-s/select pc-kbotown-today)
        fences (-> page-html
                   h-c/parse h-c/as-hickory
                   fn-select first first-content :content)
        articles (into #{} (mapcat parse-article) fences)]
    (vec articles)))
