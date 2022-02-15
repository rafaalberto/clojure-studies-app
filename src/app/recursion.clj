(ns app.recursion)

(def transactions
  [{:value 120.00M :type "deposit"}
   {:value 50.00M :type "withdraw"}
   {:value 30.00M :type "deposit"}
   {:value 40.00M :type "withdraw"}])

(defn is-withdraw? [transactions]
  (= (:type transactions) "withdraw"))

(defn calculate-balance [balance transactions]
  (if-let [current-transaction (first transactions)]
    (calculate-balance (if (is-withdraw? current-transaction)
                         (- balance (:value current-transaction))
                         (+ balance (:value current-transaction)))
                       (rest transactions))
    balance))

(println "Balance: " (calculate-balance 0 (take 4 transactions)))