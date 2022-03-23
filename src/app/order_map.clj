(ns app.order-map)

(def order {:backpack {:quantity 2 :price 120}
            :t-shirt  {:quantity 10 :price 50}})

(defn price-by-item [product]
  (* (:price product) (:quantity product)))

;option 1
(defn total-price [order]
  (reduce + (map price-by-item (vals order))))

;option 2
(defn total-price [order]
  (->> order
       vals
       (map price-by-item)
       (reduce +)))

(println "Total price:" (total-price order))

;option 1
(defn print-value [[_ value]]
  (str "value 1: " value));(defn total-price [order]
;  (reduce + (map price-by-item (vals order))))

(println (map print-value order))

;option 2

(defn print-values [values]
  (str "value 2: " values))

(println (map print-values (vals order)))