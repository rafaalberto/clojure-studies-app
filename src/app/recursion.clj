(ns app.recursion)

(def transactions
  [{:value 120.00M :type "deposit"}
   {:value 50.00M :type "withdraw"}
   {:value 30.00M :type "deposit"}
   {:value 40.00M :type "withdraw"}])

(defn is-withdraw? [transactions]
  (= (:type transactions) "withdraw"))

(defn calculate-value [balance transaction]
  (let [value (:value transaction)]
    (if (is-withdraw? transaction)
      (- balance value)
      (+ balance value))))

(defn calculate-balance
  ([transactions]
   (calculate-balance 0 transactions))
  ([balance transactions]
   (if-let [current-transaction (first transactions)]
     (recur (calculate-value balance current-transaction)
                        (rest transactions))
     balance)))

;arity overloading
(println "Balance: " (calculate-balance transactions))

(defn add-transactions [value]
  {:value value})

(def new-transactions (map add-transactions (range 100000)))
(println "Balance: " (calculate-balance new-transactions))