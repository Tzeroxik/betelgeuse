(ns betelgeuse.db.pool
  (:import (com.zaxxer.hikari HikariConfig HikariDataSource)
           (java.util Properties)))

(defn create-datasource ^HikariDataSource [configuration]
  (->
    (doto (Properties.)
        (.putAll configuration))
    (HikariConfig.)
    (HikariDataSource.)))