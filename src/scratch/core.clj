(ns scratch.core)

((fn [s]
   (letfn [(connected? [e]
             (if (>= 1 (count e))
               true
               (let [v1 (ffirst e)
                     in (for [ei e :when (= v1 (second ei))] (first ei))
                     out (for [ei e :when (= v1 (first ei))] (second ei))
                     el (filter (partial not= v1) (concat in out))
                     ]
                 (prn "=========")
                 (prn (remove #(some #{v1} %) e))
                 (prn v1)
                 (prn (concat in out))
                 (prn el)
                 (prn e)
                 (prn "=========")
                 (if (empty? el)
                   false
                   (recur (remove #(some #{v1} %) e))
                   )
                 )
               )
             )]
     (connected? s)
     )
   )#{[:a :b] [:b :c] [:c :d]
               [:x :y] [:d :a] [:b :e]})

