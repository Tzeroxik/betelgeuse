(ns betelgeuse.util.edn
  (:require [clojure.edn :as edn])
  (:import (java.nio.charset StandardCharsets)))

(defn contains-all? [keys obj]
  (->> keys
       (filter (fn [k] (not (contains? obj k))))
       (empty?)))

(defn str-2-obj [^String str with-keys]
  (let [obj (edn/read-string str)]
    (if (contains-all? with-keys obj)
      obj
      (throw (ex-info "Object doesn't contain all keys" {:object obj :keys with-keys})))))

(defn load-edn [^String name with-keys]
  (with-open [stream (ClassLoader/getSystemResourceAsStream name)]
    (if stream
      (-> stream
          (.readAllBytes)
          (String. StandardCharsets/UTF_8)
          (str-2-obj with-keys))
      (throw (ex-info (str "Unable to load file " name) {:name name})))))