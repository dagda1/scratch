(ns scratch.core)

(def g #{[:a :b] [:b :c] [:c :d]
              [:x :y] [:d :a] [:b :e] [:x :a]})

((fn [g]
   (letfn [(connected? [g]
             (loop [q (conj [] (ffirst g)) visited #{}]
               (if (empty? q)
                 (let [rem (filter #(not (% visited)) (flatten (for [e g] e)))]
                   (prn rem)
                   (= 0 (count rem)))
                 (let [v1 (peek q)
                       edges (filter (partial some #{v1}) g)
                       vertices (filter (partial not= v1) (flatten edges))
                       unvisited (filter #(not (% visited)) vertices)
                       ]
                   (prn "===========")
                   (prn v1)
                   (prn q)
                   (prn edges)
                   (prn vertices)
                   (prn visited)
                   (prn unvisited)
                   (prn "===========")
                   (recur (into (rest q) unvisited) (into (conj visited v1) unvisited))
                   ))
               )
             )
           ]
     (if (= (count g) 0)
       true
      (connected? g)))
   ) g )
