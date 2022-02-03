(ns app.reduce)

(def transactions
  [{:type "Withdraw" :value 500.00}
   {:type "Deposit" :value 1000.00}
   {:type "Withdraw" :value 300.00}
   {:type "Deposit" :value 100.00}])

(defn get-values [transactions]
  (:value transactions))

(defn deposit? [transactions]
  (= (:type transactions) "Deposit"))

(defn withdraw? [transactions]
  (= (:type transactions) "Withdraw"))

(def deposit-total (reduce + (map get-values (filter deposit? transactions))))
(def withdraw-total (reduce + (map get-values (filter withdraw? transactions))))

(println "Deposit (+): " deposit-total)
(println "Withdraw (-): " withdraw-total)
(println "Balance: " (- deposit-total withdraw-total))