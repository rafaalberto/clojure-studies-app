(ns app.map)

(def transactions {:value 30.0 :type "Expense"})

;(println (get transactions :type)) ;another option
(println (:type transactions))