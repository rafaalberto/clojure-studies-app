(ns app.filter)

(def order {:notebook {:quantity 5 :price 20}
            :eraser   {:quantity 3 :price 5}
            :sticker  {:quantity 10}})

(defn free? [item]
  (<= (:price item 0) 0))

;(defn payed? [item]
;  (not (free? item)))

(def payed? (comp not free?))

;(println "Items:" (filter (fn [[_ value]] (free? value)) order))
(println "Items:" (filter #(free? (second %)) order))

(println "Is payed?" (payed? {:price 0}))
(println "Is payed?" (payed? {:price 10}))