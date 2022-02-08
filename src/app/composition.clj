(ns app.composition)

(def transactions
  [{:type "Deposit" :amount 500.00 :date "2022-01-01" :currency "R$"}
   {:type "Withdraw" :amount 300.00 :date "2022-01-06"}
   {:type "Withdraw" :amount 50.00 :date "2022-01-15" :currency "R$"}
   {:type "Deposit" :amount 1000.00 :date "2022-01-19" :currency "R$"}])

(defn amount-symbol [transactions]
  (let [currency (:currency transactions "$")
        amount (:amount transactions)]
    (if (= (:type transactions) "Deposit")
      (str currency " +" amount)
      (str currency " -" amount))))

(defn date-value [transactions]
  (str (:date transactions) " => "
       (amount-symbol transactions)))

(def prices {:dollar {:price 5.50 :symbol "$"}})

(defn transactions-in-dollar [transactions]
  (let [dollar (:dollar prices)]
    (assoc transactions
      :amount (* (:price dollar) (:amount transactions))
      :currency (:symbol dollar))))

(println (date-value (transactions-in-dollar (first transactions))))