(ns elite-rpg.sprites
  (:require
    [elite-rpg.resources :as r]))

(defn draw-sprite [container res-id x y width height]
  (let [sprite (js/PIXI.Sprite. (r/get-texture res-id))]
    (set! (.-position.x sprite) x)
    (set! (.-position.y sprite) y)
    (set! (.-scale sprite) true)
    (set! (.-width sprite) width)
    (set! (.-height sprite) height)
    ; (set! (.-anchor.x sprite) 0.5)
    ; (set! (.-anchor.y sprite) 0.5)
    ; (set! (.-rotation sprite) rotation)
    (.addChild container sprite)))


(defn draw-sample [app]
  (doto (.-stage app)
    (draw-sprite :ship 370 500 64 64)))
