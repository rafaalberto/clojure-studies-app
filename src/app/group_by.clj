(ns app.group-by)

(def order1 {:user  1
             :items {:backpack {:quantity 2 :price 30}
                     :t-shirt  {:quantity 5 :price 20}}})

(def order2 {:user  1
             :items {:pen    {:quantity 4 :price 5}
                     :eraser {:quantity 1 :price 2}}})

(def order3 {:user  2
             :items {:laptop {:quantity 1 :price 2000}
                     :iphone {:quantity 1 :price 3000}}})

(def orders [order1 order2 order3])

(defn print-quantity [[user orders]]
  {:user user
   :orders-quantity (count orders)})

(->> orders
     (group-by :user)
     (map print-quantity)
     println)