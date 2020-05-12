(ns mpark-newsletter.util-test
  (:require [clojure.test :refer :all])
  (:require [mpark-newsletter.util :refer :all])
  (:import (java.time Instant)))

(deftest date-'yyyysMMsdd'-test
  (testing "formatting for yyyy/MM/dd"
    (let [inst (Instant/ofEpochSecond 1000000000)]
      (is (= "2001/09/09" (date-'yyyysMMsdd' inst))))))

(deftest date-'yyyyMMddHH'-test
  (testing "formatting for yyyyMMddHH"
    (let [inst (Instant/ofEpochSecond 1000000000)]
      (is (= "2001090901" (date-'yyyyMMddHH' inst))))))
