(ns betelgeuse.util.file
  (:import (java.io InputStream)
           (java.nio.charset StandardCharsets)))

(defn load-file-as ^String [^String name mapper]
  (with-open [stream ^InputStream (ClassLoader/getSystemResourceAsStream name)]
    (if stream
      (mapper stream)
      (throw (ex-info (str "Unable to load file " name) {:name name})))))

(defn load-file-as-str ^String [^String name]
  (letfn [(mapper [^InputStream stream]
            (-> stream (.readAllBytes) (String. StandardCharsets/UTF_8)))]
    (load-file-as name mapper)))