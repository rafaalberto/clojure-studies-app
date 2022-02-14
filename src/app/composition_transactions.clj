(ns app.composition-transactions)

(def transactions
  [{:type "Deposit" :amount 523.31M :date "2022-01-01" :currency "R$"}
   {:type "Withdraw" :amount 300.00M :date "2022-01-06"}
   {:type "Withdraw" :amount 50.00M :date "2022-01-15" :currency "R$"}
   {:type "Deposit" :amount 1000.00M :date "2022-01-19" :currency "R$"}])

(def currencies
  {:dollar {:price 5.44M :symbol "$"}
   :euro   {:price 6.00M :symbol "€"}})

(defn transactions-by-currency [currencies currency transactions]
  (let [{{price :price symbol :symbol} currency} currencies]
    (assoc transactions
      :amount (* price (:amount transactions))
      :currency symbol)))

(def transactions-euro (partial transactions-by-currency currencies :euro))
(println (transactions-euro (first transactions)))

(def join-all (partial clojure.string/join ";; "))
(println (join-all (map transactions-euro transactions)))