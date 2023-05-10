(ns betelgeuse.util.edn
  (:require [betelgeuse.util.file :as file]
            [clojure.edn :as edn]))

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
  (-> (file/load-file-as-str name)
      (str-2-obj with-keys)))