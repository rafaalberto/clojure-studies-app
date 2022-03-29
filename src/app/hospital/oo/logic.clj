(ns app.hospital.oo.logic
  (:require [app.hospital.oo.model :as h.model])
  (:import (java.util Date)))

(defn now []
  (h.model/to-milliseconds (Date.)))