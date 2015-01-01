(ns scratch.core)

(def x #{["cat" "man"] ["man" "snake"] ["spider" "cat"]})


(letfn [(trans [whole l acc]
          (let [f (first l)]
            (prn "=================")
            (prn "***********")
            (prn (str "f=" f))
            (prn whole)
            (prn "***********")
            (let [rels (reduce (fn [a c]
                                 (prn "--------------")
                                 (prn "f:=" f)
                                 (prn "c:=" c)
                                 (prn "--------------")
                                 (if (= (first f) (last c))
                                   (conj a [(first c) (last f)])
                                   a
                                   )
                                 )
                               [] whole)]
              (prn (str "rels:=" rels))
              (prn "=================")
              (if (empty? l)
                acc
                (recur (concat rels whole) (concat rels (rest l)) (concat whole rels acc))
                )
              )
            )
          )
        ]
  (set (trans x x []))
  )




