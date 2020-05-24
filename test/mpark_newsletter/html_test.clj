(ns mpark-newsletter.html-test
  (:require [clojure.test :refer :all])
  (:require [clojure.java.io :as io]
            [mpark-newsletter.html :refer :all]
            [mpark-newsletter.util :refer :all]))

(def li [{:type    :element :attrs {:class "info"} :tag :div
          :content [{:type :element :attrs {:class "nick"} :tag :span :content ["Ace.최동원"]}
                    {:type :element :attrs {:class "date"} :tag :span :content ["1시간전"]}]}
         {:type    :element :attrs {:class "title"} :tag :div
          :content [{:type    :element :tag :a
                     :attrs   {:href "http://mlbpark.donga.com/mp/b.php?b=kbotown&id=202005110042487481&m=view"}
                     :content ["단장님 경기 중 엠팍 했을 것이다 vs 안 했을 것이다"]}]}])

(deftest parse-li-test
  (testing "parse li content"
    (is (= {:nick  "Ace.최동원"
            :url   "http://mlbpark.donga.com/mp/b.php?b=kbotown&id=202005110042487481&m=view"
            :title "단장님 경기 중 엠팍 했을 것이다 vs 안 했을 것이다"}
           (parse-li li)))))

(deftest parse-test
  (testing "parse"
    (let [page-html (-> "page.html" io/resource slurp)
          articles (parse page-html)]
      (is (= 17 (count articles)))
      (is (= {:nick  "J.Cueto."
              :title "트레이드 진행했군요ㄷㄷㄷ"
              :url   "http://mlbpark.donga.com/mp/b.php?b=kbotown&id=202005110042487403&m=view"}
             (first articles))))))
