(ns app.composition)

(def transactions
  [{:type "Deposit" :amount 500.00 :date "2022-01-01" :currency "$"}
   {:type "Withdraw" :amount 300.00 :date "2022-01-06"}
   {:type "Withdraw" :amount 50.00 :date "2022-01-15" :currency "$"}
   {:type "Deposit" :amount 1000.00 :date "2022-01-19" :currency "$"}])

(defn amount-symbol [transactions]
  (let [currency (:currency transactions "$")
        amount (:amount transactions)]
    (if (= (:type transactions) "Deposit")
      (str currency " +" amount)
      (str currency " -" amount))))

(defn date-value [transactions]
  (str (:date transactions) " => "
       (amount-symbol transactions)))

(defn transactions-in-real [transactions]
  (assoc transactions
    :amount (* 5.50 (:amount transactions))
    :currency "R$"))

(println (date-value (first transactions)))
(println (transactions-in-real (first transactions)))