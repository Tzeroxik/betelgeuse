(ns betelgeuse.util.edn-test
  (:require [clojure.test :refer :all] 
            [betelgeuse.util.edn :as edn]))

(deftest test-passes-if-has-keys
  (testing "should check if object has the expected keys"
    (let [obj {:a 1 :b 2 :c 3}]
      (is (edn/contains-all? [:a :b :c] obj)))))

(deftest test-passes-if-has-some-keys
  (testing "should check if object has the expected keys"
    (let [obj {:a 1 :b 2 :c 3}]
      (is (edn/contains-all? [:a :b] obj)))))

(deftest test-passes-if-translates-string-to-edn
  (testing "Tests if translates to edn the expected object"
    (is
      (edn/str-2-obj "{:a 1 :b 2 :c 3}" [:a :b :c])
      {:a 1 :b 2 :c 3})))

