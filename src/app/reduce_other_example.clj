(ns app.reduce-other-example
  (:use [clojure pprint])
  (:import (java.util UUID)))

(def products ["Desktop" "Laptop" "Smartphone" "Keyboard"])

(defn convert-vector-to-map [products]
  (reduce (fn [items product]
            (conj items {:id   (UUID/randomUUID)
                         :name product}))
          []
          products))

;(defn convert-vector-to-map [products]
;  (reduce #(conj %1 {:name %2})
;          []
;          products))

(pprint (convert-vector-to-map products))