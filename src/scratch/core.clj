(ns scratch.core)

((fn [nms]
   (let [nm {\I 1 \V 5 \X 10 \C 100 \D 500 \M 1000}
         red {"IV" 4 "IX" 9 "XL" 40 "XC" 90 "CD" 400 "CM" 900}
         ]
     (letfn [(parse [[x & xs] acc]
              (if (empty? xs)
                (if-let [final (nm x)]
                  (+ acc (nm x))
                  acc)
                (if-let [comb (red (str x (first xs)))]
                  (recur (rest xs) (+ acc comb))
                  (recur xs (+ acc (nm x))))))]
      (parse nms 0)))

   ) "XIV" )
