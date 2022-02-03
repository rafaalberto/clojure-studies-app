(ns app.reduce)

(def transactions
  [{:type "Withdraw" :value 500.00}
   {:type "Deposit" :value 1000.00}
   {:type "Withdraw" :value 300.00}
   {:type "Deposit" :value 100.00}])

(defn get-values [transactions]
  (:value transactions))

(defn withdraw? [transactions]
  (= (:type transactions) "Withdraw"))

(println "Withdraw Amount: "
         (reduce + (map get-values (filter withdraw? transactions))))