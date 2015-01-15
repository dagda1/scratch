(ns scratch.core)

((fn [chain]
   (letfn [(debug-print [x]
             (prn "=======================")
             (prn (map #(prn (str % " - " (meta %)) ) x))
             (prn "======================="))

           (find-match [left right]
             (let [potentail (first (for [x left y right :when (= x y)] y))]
               (when (= ((meta right) potentail) :free)
                 potentail)))

           (record-single [col item match]
             (assoc (vec col) (.indexOf col item) (with-meta item (assoc (meta item) match :visited ))))

           (record-match [left right match coll]
             (record-single (record-single coll left match) right match))

           (all-visited? [s]
             (every? #(= :visited %) (flatten (map (fn [i] (map (fn [x] ((meta i) x)) i)) s))))

           (filter-visited [s]
             (filter (fn [item] (not= (map #((meta item) %) item) [:visited :visited])) s))

           (find-next [item coll]
             (if (empty? coll)
               nil
               (if (find-match item (first coll))
                 (first coll)
                 (recur item (rest coll)))))

           (can-tour? [s]
             (loop [[x & xs :as coll] s]
               (if-let [next (find-next x xs)]
                 (let [match (find-match x next)
                       updated (record-match x next match coll)]
                   (if (all-visited? updated)
                     true
                     (let [updated-x (first (filter #(= x %) updated))
                           updated-next (first (filter #(= next %) updated))
                           rem (filter #(and (not= x %) (not= next %)) updated)
                           next-list (conj (vec (conj rem updated-next)) updated-x)]
                       (recur next-list))))
                 false)))]
     (if (= (count chain) 1)
       true
       (let [init (map #(with-meta % (zipmap % [:free :free])) chain)]
         (if (can-tour? init)
           true
           (can-tour? (reverse init))
           ))))) [[1 2] [2 3] [3 4] [4 1]])

