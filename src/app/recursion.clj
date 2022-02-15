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

(defn calculate-balance [balance transactions]
  (if-let [current-transaction (first transactions)]
    (calculate-balance (calculate-value balance current-transaction)
                       (rest transactions))
    balance))

(println "Balance: " (calculate-balance 0 (take 2 transactions)))