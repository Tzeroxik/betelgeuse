(ns betelgeuse.util.file
  (:import (java.io InputStream)
           (java.nio.charset StandardCharsets)))

(defn file2stream ^InputStream  [^String name]
  (let [stream (ClassLoader/getSystemResourceAsStream name)]
    (if stream
      stream
      (throw (ex-info (str "Unable to load file " name) {:name name})))))

(defn load-file-as ^String [^String name mapper]
  (with-open [stream (file2stream name)]
    (mapper stream)))

(defn load-file-as-str ^String [^String name]
  (letfn [(mapper [^InputStream stream]
            (-> stream (.readAllBytes) (String. StandardCharsets/UTF_8)))]
    (load-file-as name mapper)))