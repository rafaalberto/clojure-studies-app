(ns app.orders-database)

(def order1 {:user  1
             :items {:backpack {:quantity 2 :price 30}
                     :t-shirt  {:quantity 5 :price 20}}})

(def order2 {:user  2
             :items {:pen    {:quantity 4 :price 5}
                     :eraser {:quantity 1 :price 2}}})

(def order3 {:user  3
             :items {:laptop {:quantity 1 :price 2000}
                     :iphone {:quantity 1 :price 3000}}})

(def order4 {:user  2
             :items {:notebook {:quantity 5 :price 30}
                     :pencil   {:quantity 10 :price 40}}})

(defn all-orders []
  [order1 order2 order3 order4])