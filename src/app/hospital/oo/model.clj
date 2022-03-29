(ns app.hospital.oo.model
  (:import (java.util Date)))

(defprotocol Dateable
  (to-milliseconds [this]))

(extend-type Date
  Dateable
  (to-milliseconds [this] (.getTime this)))