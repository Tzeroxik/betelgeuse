(ns betelgeuse.util.edn-test
  (:require [clojure.test :refer :all]
            [betelgeuse.util.edn :as edn]))

(deftest test-passes-if-has-keys
  (testing "should check if object has the expected keys"
    (let [obj {:ok 1 :boomer 2 :banana 3}]
      (is (edn/contains-all? [:ok :boomer :banana] obj)))))

(deftest test-passes-if-has-some-keys
  (testing "should check if object has the expected keys"
    (let [obj {:ok 1 :boomer 2 :banana 3}]
      (is (edn/contains-all? [:ok :banana] obj)))))

;(deftest test-fails-if-missing-key
;  (testing "should check if object has the expected keys"
;    (let [obj {:ok 1 :boomer 2 :banana 3}]
;      (is (t (edn/contains-all? [:d] obj))))))

(deftest test-if-translates-string-to-edn
  (testing "Tests if translates to edn the expected object"
    (is
      (edn/str-2-obj "{:ok 1 :boomer 2 :banana 3}" [:ok :boomer :banana])
      {:ok 1 :boomer 2 :banana 3})))
