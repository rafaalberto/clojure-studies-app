(ns app.order-map)

(def order {:backpack {:quantity 2 :price 120}
            :t-shirt  {:quantity 10 :price 50}})

(defn price-by-item [product]
  (* (:price product) (:quantity product)))

;(defn total-price [order]
;  (reduce + (map price-by-item (vals order))))

(defn total-price [order]
  (->> order
       vals
       (map price-by-item)
       (reduce +)))

(println "Total price:" (total-price order))