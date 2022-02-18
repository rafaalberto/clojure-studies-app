(ns app.recursion)

(def transactions
  [{:value 120.00M :type "deposit"}
   {:value 50.00M :type "withdraw"}
   {:value 30.00M :type "deposit"}
   {:value 40.00M :type "withdraw"}])

(defn is-withdraw? [transactions]
  (= (:type transactions) "withdraw"))

(defn calculate-value [total transaction]
  (let [value (:value transaction)]
    (if (is-withdraw? transaction)
      (- total value)
      (+ total value))))

(defn calculate-balance
  ([transactions]
   (calculate-balance 0 transactions))
  ([total transactions]
   (if-let [current-transaction (first transactions)]
     (recur (calculate-value total current-transaction)
                        (rest transactions))
     total)))

;arity overloading
(println "Balance: " (calculate-balance transactions))

(defn add-transactions [value]
  {:value value})

;using recursion
(def new-transactions (map add-transactions (range 100000)))
(println "Balance: " (calculate-balance new-transactions))

;other way - using reduce
(println "Balance: " (reduce calculate-value 0 new-transactions))