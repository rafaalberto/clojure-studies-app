(ns app.reduce)

(def transactions
  [{:type "Withdraw" :value 500.00}
   {:type "Deposit" :value 1000.00}
   {:type "Withdraw" :value 300.00}
   {:type "Withdraw" :value 100.00}
   {:type "Deposit" :value 100.00}])

(def deposit-total (reduce + (map #(:value %)
                                  (filter #(= (:type %) "Deposit") transactions))))

(def withdraw-total (reduce + (map #(:value %)
                                   (filter #(= (:type %) "Withdraw") transactions))))

(println "Deposit (+): " deposit-total)
(println "Withdraw (-): " withdraw-total)
(println "Balance: " (- deposit-total withdraw-total))