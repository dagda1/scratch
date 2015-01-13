(ns scratch.core)

((fn [chain]
   (letfn [(permutations [s]
              (lazy-seq
               (if (seq (next s))
                 (apply concat (for [x s]
                                 (map #(cons x %) (permutations (remove #{x} s)))))
                 [s])))
           (find-match [left right]
             (prn left)
             (prn right)
             (first (for [x left y right :when (= x y)] y)))
           (can-tour? [s]
             (loop [[x & xs] s]
               (if (empty? xs)
                 true
                 (if-let [m (find-match x (first xs))]
                   (let [updated (assoc (vec s) (.indexOf s x) (with-meta x (assoc (meta x) m :visited )))]
                     (prn (meta (first updated)))
                     )
                   (prn "not found")
                   )
                 )
               ))]
     (if (= (count chain) 0)
       true
       (loop [perms (permutations chain)]
         (if (empty? perms)
           false
           (if (can-tour? (map #(with-meta % (zipmap % [:free :free])) (first perms)))
             true
             false
             ;(recur (rest perms))
             )))))
   ) [[1 2] [2 3] [3 4] [4 1]])

