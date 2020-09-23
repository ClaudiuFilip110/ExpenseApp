package com.example.android_resources.screens.action

import android.media.Image
import android.util.Base64
import android.util.Log
import android.view.MotionEvent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_resources.data.database.entities.Action
import com.example.android_resources.data.database.repositories.UserRepository
import com.example.android_resources.screens.action.adapters.ActionsAdapter

class ActionPresenter(
    private val actionView: ActionView,
    private val userRepository: UserRepository
) {
    fun getRecycler(recycler: RecyclerView, actionActivity: ActionActivity) {
        recycler.layoutManager =
            GridLayoutManager(actionActivity, 2, GridLayoutManager.HORIZONTAL, false)
        val list = ArrayList<Action>()
        val listImagesUrl = ArrayList<String>()
        var action = Action()
        action.categoryImage =
            "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAP6SURBVHgBxZZdaBxVFMfP/Zid2Y1a11pDkCZt+tA+aLNdRKFpSEqiQoWatokfCGpIal0FX/ogVKz44otPKkFNIkGLH4R8YEq7kbRpURQVpSFZ/EgrhWrFNVJTE/ZjPu7xjpDN7vZuNjem9PcyM+ece89/7j33zADcYAisAvtcJMIZHpXD7xOzbiUwmIUAOcx3Tn8EmmgLcKciT1CAfjnS8J+9pLs0mQXv8F2JGGigJSD17V0brSBPyNtbFm0FAiiiw0RTsPHHz2GFUNDAtFhbfvJiUBBiMPYiaKAlQL5jXdkIQbaBBnoCCFwuF4IgC/J6CRDonSkXgwzjoIH2KfCm6z4mQB7LPecVITXgV9aYqAYNNGsA4NJfVzsQxOt+yeXbiUm+/0csbAdNVtWIfOa+uDtccRPeQ1Mk6mRw0GpO/OLbL49WhSprNjwOWdgDNnqeA0fN3dM/rbkAFelzkU0mh1PydgumBIh5IRPIlQrSZ3n9dK9qjPYWLIfJoMdPnm9D2Z7kSnT7KwPXU8CV8dp1QLBF5UMPjMrbNrSrfHxP7IU+QGwhRJ4yQeJoZ7vj/e/+AJqY640KuaMltxSJuF1lpwShUx6rGhmxWQ5/jprmdw/FYmHQpCL68+/yckHplLIcYn+icnGFLdi5fduV46eHliwIf8oON4oUR9juA3FCpGwFnhCHGKFji1/KXH4LjoV2zii7qLIGLl5dgKI3uEOuThdFcgLPDE2kxwdrVeOMuqkJ14N7EXFAbutvxIAkWHCE1yeehBJw0AVJk0lwyjs1fIQ273ureDUCOyYn5eXRlU6nL8CHkAp5vt/A00Nt3khfXJZvOudjJH3pb/b+5o6OzEqmWp2AJSENkE41FJurg7TbHu3dFdh78OtyU6xpI8ohBKMuvFTKbQ/0RNxPew769/9vBZaDkZFik3O8r5lm3WNgZ6pAfkTd4Z4uTQH4DQocA0pzhYeW1VQQItAFpG/zh7uGF03pE301gYw3DPOpaH4ocbJRLQGeRw4bD7R9WWR+tVT8xf5+a+M65wMyn2mT23Jtl6RmQksAY/ieNzF0tsA4N9ugDEbZOdyFLZDBgNJvWJNz2VCjUkDYNEAN2SpP/daCPBkb9CACQqGXWWvna/7TNQJkF8OmTZV+M9kBaw03/nA4a7RaO2cWTVT2+fM5bSAuIIrna/c9FfU88aCU8xWsBXI30Ax+yB6JVVn7n5kpcLW3twfT4XCNkJlP9vb6Ygpaq/PZwP2M01dkaL1qbkyW+VPnPCV44ICxv2tMqQ1WiDM+2EIZeZoIbJBvVF1WAGUOGsbZpIGtd+49lCo176r+Cc+/edLkNyf/G3srzFnF/tT6kL1c0nz+BQE8aJl+mYkCAAAAAElFTkSuQmCC"
        action.category = "Income"
        list.add(action)
        action = Action()
        action.categoryImage =
            "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAVASURBVHgBtVd5bBRVGP+9OXa6V689uq2IaFuiEA4l1SJ4QRT+QVKORqVEI4mJ0RiDJkpilMRESEyMRxOJikYDmqBoohhKQPDgqASEACLhaGwptKXtXu1ud3dm3vPNlK073e22VfaXTGb3fd973+99v3d8QzAJvL1nrZ/K7A0G0kcYnJTQsxsWbf80X5/ObSum2B3CxwyYzSh707dq55ZMO8EksHn/mmZChOcy25imL3314S/35PK/smP1VEWkhwghU0xfsHhfSA3cvu77gUkReKdldXlKUVoISN1oG2OgAsEP8V+qV2zcuJFm2vp3rmwBIUss/pQ95V218/P0fwETgCorT+QKbs6AQODpXV608FJjZnvv1w2PZQbv7E4iHNWMDjWZfuMS2Lz38YUQyJPZgQUEXNOwYOoqVPA3RHy2aX/TM4aNZ0IgovD6tX4Vvx2L4KcjYZxrG4IsEy4D7bSMky/4pn1NSwWR7M5s8zqqsHzmepTZvRCEYf7xVAzNh5819NDlVNLfNMAWiyJ2HOTBAz4Ffo+MIkWAjROgur7Mt/q7XenxpHwEiEDWjm6bFXgAHqff0B4JVeePhhOd+9JpETVRekgkqdeMuS2YV2JIZAFjzJKBvARgpsuq0oG2rxCMR1FdtsQkYaBEqUVp0RSEE52oS+p1XLLZw3yG7Ze7kogO6phZ64A+mLwyHgGjGxseQLgnhx26Lo4EN+BWqvDgtJdxqKMZ08OnlxltZ87H0NWrQtUoJEnA/DlucxsGnt7dNyaBResqbyE2+zEQuktKOl7iUWpL+1Jw8tV79TYnGKfmtvlR41mcg7WAGSX3o+jK6RnBsIZgREP9XDcUWTAXn5ENykgruT65NCz53b+1q52qZCkfLkDkoR8bmjt+b/jwMh7Z3oVHP+qAnKIo5rMlY6xdZ/9R811WImHBXcVwO0XYbGRECgH6r6P7ZG3DA59cOr71bMW2LRf800tDakO6vbwnhfm7eiALNuSCThOo7D05nA0eUBSJKdOpczGEeDYM8FMqi4BFgr/vm1dpF+Vv+dFZD5o9y+ozMbR3XAIqswnEQ8e5cipSKsPxPwcQH6LQuP7Fbgkl/OGJT11oF1vzErALtibOvh55UH7iArS6JCRBsbS7e4+Y73NtcbjsImbWOCFLBIpNgHlcMHbs3vXfDI0ezyKBDtrNJJLKiiqKifRPOaEhkuiAkVDGhl0TahCe0Hnz9x3VDm3WdJde7BJhL7oenIPYiw/H/vigCvky4Hl/zc38suhMtbZ16G09LvnuW4fk2kANEUQvi8SOhg7+ZSNVZK4jcgCB2BF++hJ0cZ01jaC71IsyV+CaV/G4eHNSS0ROJUMdblEuUuXSm4go29dTjf3Mw1zNjGkROnn6vQ0E4lsoEDRK5zvmvGBZBxYJGCNBFBAaU7PGtxDgO7agBDiD/ARoYTPAWtqmhpCfAC0gARJtbGzUkY+AQIUCEsg9OQuBwXiskBKMT8C38JUBfmSqvGhAOBxBMBgeeaLRARSCQFY9wO/sIL8LKiReU1Hy781p/P8/YBMlwK8yw7FCttl4CW2pspFMpqAouW/D8TDWFpeyHVnQuEajkQHQUQSMItTrK+ccJ/U9Y4JNlACDwCVg8Pk9uJFgTJugBPwsMJTv6e7NykAa/gqfaevr7YfDYUdpWQnGJTDRDHDXi8ZHh9fn4ayzCRjlmCQZC1I0syROcHEyKl7M1Z4l5mDruxU2p9jOTQpuEHhGTyqznr8zly2rJnTVv9jD7+2VvNtejKpg/0PoIb6tv0hRfe1YHv8AlxAINRNpqv8AAAAASUVORK5CYII="
        action.category = "Food"
        list.add(action)
        action = Action()
        action.categoryImage =
            "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAVGSURBVHgB7VZdbFRFFP7m/uze/SnbbaVdSvnRgAXTEokiiCKYmgaTgiCgSMQHIVUTCQ/CCyY++mJMIDwQSDRRDBEw4UdCQKpCMMKDCcYiVihQ/trdlLa77XZ3u/feGc/MdgtsMbRY0xe+ZPbO3DnzfeecOTN7gUcYY7AHGfzW0GAWx2IRS9N8NPSBMfXUmeETcpwjSbvCSVM3DSHUM8N5Ol5eHn121y77oRy4tmZNOA7/pz+WVL99KRAJZnQP+jVzSJPwcntIs9wspvdFk7Vd578pRmrLlD17uoftQPPSpUWt4em/flS9rjptqCARsHS4XCCT5Wqsa0yNJUxDg+sKcCGGcPkoMZ+f/+L81O5L82ccPtxbOK/dz4FM4LFtW2a9Wx0sDSkhiZKAByF/LmK/V0dF2AtDz82VjfOgOGjksmFqZGcMOik5JJfkvJ/WEAdali8vOxB5bm2/rwg+U1fRFaZKEjPGBuekj2zAImgZCPlyjkoHJYfkkpyS+4EOXA9P++RQ5UvGQOAkNCDOpBAbFLzXKXavhwO2+ezJx4EJLxqx4qkfF+qxtvXrZ5quWMWZOBS17Zats9Z1t5RVmZJA6tkOh0177ffoakHG5vBQZJqsAdr3ZNaliA1wsunJOBhHGZBzDs3pGlSmZGmkbBc1nRftjU1fhiOmOU0T7DVbZ/sNk7OvyMU5ZFbnOr4jV8ueNPOeS8g0m3d5bJl3kqaTI1JcRUlriv13LPP1kc9igAK4Ul5lst+tD5lHLKGXL5gc9YatiQ8oA6vJyYq/Sx/fVNR7Gw4dL1fTVRPUuHwyTeWSqT35l9NLoQoZLmWDCQ6Nu2DU9IFm0PH8s+SJzeV9Lccp4DOUgW8HmW6ufLOekvw9homkYaHH9Kv+ODuFoJMZ7lK4wJLK7/YeUdmRP/tWrdIXCHaOhjWUS4qA43/BILdoOs3E7Df273fVBi7gaGB+X03p5qXQzU5kM7ORPHAE9oVmjAbMp2YguLweHuscXLsUnZ8drlmQSjXQ1A5VUYKxjd55c6EnjkHc+gGeSoHSTSsQem8t/iskR+mm1xWn5JYaUktqynnj1srV86kgquy/msHrlkFz28GCFRA3tsP3TD0SZHQm6Kd7XxuRsJdS/XwyRRwhiOgesEkbgMrF4PoE2HuPy4urSmobVHivyhvcjcbQsWU3jMmV8MzywKpZhMwvuS2QhXK2KDgSfSxK9Khnz8FmWE8vRGbfT8j+0QzneqM6LQrcWWw4gnfp+WNFE861G6ql7joPc/pSuOiz0KfrwxIPuK5aI5E62kjt/naMaW10X7CbDyLUyeF3Orow2qA6uKJxbl/GGMHhdouxj/MrK5gQVqnNwu/fBu/V0LU9goRp4Gy4GG2WV3oKMUJyualEi4pMP+Z1xxGyHZRsiEIr4ujeMR7pbiO7VdfbjUPxeGZuqKRtusUnavSRldgdIlEPDk6IIDtQ+Y7j0P0xsstJo7WGaVIgJi4H/FjWHoV5MIRwQxeYz0V7J7t5rqtTKIWr4FG3w4PObeORaAvgaHnZoLiKhrGHanlILskpuaWG1LpsO7flnLoJTzH4Wysm54yDDJmCapfRaCO8BwqRNAx8PWkiPPIIBoCOWKwIvYmcA1f7+mbeamq6TipqqyNe78+vRCLHMIo4HY8vbunvf1kNOGc96fRM2TXyBlkhJtMXhuq3JpOxt3bu3ItRRG1t7WzK4pTC99rJkyf76T/82t0vaf8uYJRRyCk1pbaR64u5rusuNE31udN24sSJ0xhlNDY27q6rq2ulboVt25yycUpq4xHGGv8AQNIdDuW1cRYAAAAASUVORK5CYII="
        action.category = "Car"
        list.add(action)
        action = Action()
        action.categoryImage =
            "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAOCSURBVHgBzZZbbBRVGMf/35nD7LC7LSxqjCzFmgheMPFWEyI1GiDGW9IEFI0vaoIPRF820QdfFB7VRE180KCGF80CPkAI8FCuCSHhXii3NmlS7oWWQkvbbHfmXDizXLKF7nZOZxP4JZOdOZf/+c53vu87S4hB95pGL/tkagUCfOX0qkeEp/9OfNTxrY0GQwyyDcnPCPQLAQ0I1HQ+pL8R6557z0YjlgFgbDlB+0FRz5Ep9mepTetVNhIcMSCl8hrIkx9wJp13brf2WmkgJidWwn1m/rw+dknUg5OQHua7yzoOR50fywMh2RcbkyAyi0P6np47dVlnt838WDEwuv2FD1J16R2Q5iAEHFfQHvH/s0tsNMYcgZGh9e1vLFdSZytNYEx3t/QN9jOGH8zkplKbr8+jR2SNwK0NeXTxcop9vyWDFpCeVknL4U7rGAPWtr3+GjF2ABOwaPAGpgkVvh6RQq3y3j616fqaxunpqd4/rKhbIOHszDJ08qCqjknhsTFgNpCKEpU93OlK+yrnLT6x+U5b5oszA+ZnaWhIXcpbfcXVH0JVD3J912WWnE4mz5UvXk5oyL+P8u8GdRgYEzMpAzTRwrVHF3w9Xt96k5ajXO7SWkfSnnQaEtHv+bYFTaTkjx+/uq/jv61P1xUz3qdXR4OflQjSkXXKP/JtzW+Z6N4FS5SSw/3X+iIvWk68u+A2xt2TWrxmBsRhjAFOQbabzDhltjRYeoAiakyY+8xcoeHDOb9YNU9NTKwMK94EmpBS4Nr1q4hCIpHIfdl88rc73w/XETwISnVg5EjrTFdOmXtvZxs2PDGs+0rvSZWGq7xxRbRSGOa96BTH4Ztr0coAeXB3jrT+CQ7dV5Sa9JLSFVmiMAz41WOy2fkE2/AXumQXosKIIWfKWuw/JiFkjH2FvW81h5kisgM15KxutxrPzx0/s2J2Y/0AERYikLOMz2eMN7B0uVVIWm22LihAp9yL/VPsKjlv8Ib+wOWhzye6O8VIAbLoVx0zB7NQeHweDmVOIiqmIGERakjDyFNW400dUL8aJ9rlThW1Y5mDVlO4//zLG91C4SXyxbvGH49VHDg0AFYcrSikmEZP/SVckP2wMiAh1Wq43mK41QeSCTTH9yr2O+aZrTN4ky/FNrEOUTFOYzNRQ9I0w2o8k0zkTLU7jRpQcEawR+Qr9jMiKZVuLW+7Cc97QnmGRFr1AAAAAElFTkSuQmCC"
        action.category = "Clothes"
        list.add(action)
        action = Action()
        action.categoryImage =
            "iVBORw0KGgoAAAANSUhEUgAAACAAAAAhCAYAAAC4JqlRAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAUuSURBVHgBrVddbxRVGH7OzOxsd7dbp9ACpcUOGIkSIn5c9Ubb+AOsUS9MJMAvEO81SIzXyC+gRBIvNIDxQm9MGy9sMAErGBE03aEttNi13bLdj/n2PWd3ht3pzLYLPslkZ8/H+zznPe/7njMMXWJ9WtNUZD/04U/S35ebzXPw/PO9b65MoUuwbgavT+/TVbBpH9ATjBkW/In+iRUDO4SELtCJnIP3pcCuoAts6wH/wq8a+monncx3b5iZqUnsAOnq6atK/fVvYWGGvT9mdBqrJBJ/PTsOWT4DWOOADA917BQeW5kEkyaRJjtXfpmB455l743N7EiAWLFmXSB/TgqnRiB5eyDZR8HoNwSrwFMKcJXftzL4Pi1EGichU6iTkIhH2rbA/2pWR1qaRsw+e3IBPhHJzlEkgfe7qWuQ3IPiiYEB05toFdEu4PK1AjoE2f8DNoeN1AQ79UopFLDw2ZeTKUU5p6RVXe1Joyefg5rLJprwGU1TJPiyTFvhNRptF8z3E+dYlSrq5Qoc24ZVqxmO7Z468MnxGREDmZ6ekmWaulWtgT+bayXIqRTyg7uQ1foapLIEL5uGl0mL99i1uR6YZUMu18S7T8/m2jqqpUdwbad1qK6qqsZfhKX+0f0nosZcUlp68BCrhUVY+QzsPRrc3kwieSiSBPKxdr4Hq0vLKK+uRckFBg6OvCVENwOvUDSWxOpbofRm0XfsMCTalieBVzfx6OZdOOVqWzv3qrZ/L7Ch9ktU3sZ5464D+8ntShv5M6+9+MTkHHzuM68egZJ/HE/B1gr0mSfJn5JwhUTuG9BHIElSYyKRM0XB04IpshARLGRAHxYiGgqlYxLlgd6qLrdbQ+/h0UTyH279iWJ5M7bvpzvzuF5YihWRP3JIuD4k56AixSNKbx3cO7wP6mB/LMFCcR2Xfr6O72/die2/NHsdl2/cjO1L9fch/+xQtFljVHzaktfRciKSk1CkXM6qdCNIp7rq45CqJpSNSlvb1rNAjZ8cYICK1JP0CdsxwkiAb9Au6eEgeWdXBJ5aNhUYn3JcIWKeNVKmc8Zw2z6jsPO9oMlQ6Ng0KBj0YMB28GomyrfnYa8/2tKXPTSC7MHhzgY4hdt8Z8wgSd5vQR8vn9uRl27cjiXnqM4vYfPuQkcbbRzELcH1ryYOiKBSuC+qWyfUF5dJYDmePFqSiVtq3lRKQZtUt5AEd7MRwTyXh154DrldjXTd+7yO3aMj4TinXEkQ4Lb+NTh3Y9N97/xjATaS4DUN+J4Lu1aHazXE1qkw1VuKk+84sfPlzZazxvfOCr4Ga88XXJFQyY/TSvz9L6jpHm1T8d591Jse2VgporJWahm3NR15DWjZXoO9MzYVCmBv0+3E9U4FvYyKBQ+4KHKHRrAdeM2PVlJh69+Nxw3N1YcCBCmPhWYHdyGP9qgImR/PL9HxnHBO8Fqg0SEWJee2wtsScQSrF7xRI/43s1Ou6554+JchVsMJW49TYZQywVpdh/nPWkMYFSBe69NDg23jeLqW/5gX43mgyrJ8kb07drJ1TOyHiXnxx0+Li8tngv/ccI4KzHaVLlwEebBKKVtbWAnbBkeHz6vHJ05Hx8b6cq2wMAUlFQowl1fFw4VkhwaQ6qUDKyW3zWGOB69C90kKTovqQDQTivcenIvj6urGwUVkqJYrWsN4ULqD6OaXT5O2phs81ZVnu9K9IxtJHfc/v0SfQls/Ulha+WhIH52jQ0yjjwIt7JC8ueW/F8d909niaor/mZGPP5iI4/kPumov7zh3wl4AAAAASUVORK5CYII="
        action.category = "Savings"
        list.add(action)
        action = Action()
        action.categoryImage =
            "iVBORw0KGgoAAAANSUhEUgAAACAAAAAlCAYAAAAjt+tHAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAR1SURBVHgB7VdfaFtVGP/df7k3N2mav22TJjbrtnZbx6xsDvxfBN8UqmJ9VFBQn/yLoC9zItM354Poq+xtsE1QEISiKMOhTDv/FGx1q3Zbu7Zp2uYmaXKTe3bOCUmXNc296Tr6sh98nAvny/d95zu/7/tOgDvYZghOFYeffmVYBPnYTo8IwpJIcPzUqc++gAPIcAhJsI6UBeEoCKaaKlokSSS8R7+2NgBCBL9oSd+dPv3pf830hodfTkLCETiEiG1GwwzsfuPM825dT8a7YyAiGfvmpQNfYhPY9dqZId2rDzWzIzz++firLlUZSsS7/KJUieeHH88N0rv2d3VF4PHqU92x6FThyj9Daveu7+2ckrzhLxnppBJJjJ3/9ffBrJHjdlRVXerpiY/dqCta1icygXW8IxKEJErsovmGW9OQz6/yb1kUkwIhSS22k+0P2QUgaB64qDBdbrPqTBT9wk2/pxUz2PAK9g/0obBahKq5oKkqNov+vh12dvwNA9Ddbi7NYJolLooic9msHcdlWMVCKo1LU/+zlELX3Xxl8Ho8CAcD/LStoKUA/hyf4AF0doRqjqswslkuLIhQKODYpuMAqs6jlNEape6DUYJk0AWvKuGP+TLGZstI5S0sLKZRtix0REKO7DoKYPryDJWrSPbE8UCXhcd6gM5gWy0LeyMKRvYBJ/8qYHSqiPTSMhRZRiDQbmvbUSf8e+JfhGla7+0EnthJas7JwgKEVKqmNzKg4v64wr9ZJiyaiVsOgJ3eLJXQE/Hi0bjFWV09efH1N+F+/4M6/Wf2qdBlgTtfXs7YmbcPYGZ2jjvt9RGEdIF1tKb6uiLgvnjlZhkp7SCbplmpabm0bvMiLbfljIF4rBP3RAhcigsklwOoSJLEhZ8ineYroYES2kX7QjLlgokc7aYpSlxWoqLU+KzyxUvTPMUbNYy74lG+BtQST33pxAkoP/8CX4CWWiTC91zHPuJr8dBB5J4dQaJ9zRnjApON0NI4FsXKA0oQt26Ky4lEFAk6LnVdW7c5PT3DS4qVU7ogIFoqQ3/qSZBHHoYhKzCPfQhvWxuKL77A028FKw1oIbfG/nafF16vZ13jqgXA+zV13ugK+vt6cfbc+Ur7XWlHf6gEIRwGqDDGFItFOvQIzP0Ddb+7MFvhEyNsV2cHmsE2l1FqYGUlg7MzIjJ5StaSafcTjF0r8zXg99nq2gaQoCQk9PG8mMlj9IoE+sCgp66kWHn3Hay+/Vad/leTBd6S2YS0m4SOAmCG9uzu5eXEsvAtfZIaRpYHIe7dA6u7u8751xNF/u3ztW04pm+Eo1nQuyOBmWtzmJtPYRQhjKcJHooZ6A1r8GoSJhct/HTZxESqkno3PXk46GwiOp6Ghw/ejd8ujPNM0NvFyUm60BPfDOacNS6ncFzQLJ2HDx2gnIjR9jxPu1y+br/C+AhvXGILfaLlFxEjJZPllbVBw8jm5L63JIAq2inJtgI8V+UywXZBpm/1o0bWeM7j1jacWLcNFnH0B/YObiuuA6QYkmvbo5v4AAAAAElFTkSuQmCC"
        action.category = "Health"
        list.add(action)
        action = Action()
        action.categoryImage =
            "iVBORw0KGgoAAAANSUhEUgAAAB0AAAAiCAYAAACjv9J3AAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAR7SURBVHgBxZdLTBtHGMf/M7u2117bbGJMWylpNqrSAzng9IGUXrKAOCRShFHiXHqI22N7CFKlXoFzVREOnCo15gpR4oqQSmkam0NTqVS1K6VS4cKmiZRSAjj4/didzjoC8TD4AVL+krVez+P3PWa+GQNvQASHUPpHTSMCVQmDyihSrGImPRfj8XrjWoLmHmo3GOhI3pCVZO4jmKYJu5lGh30VqvxM50ZECqI5fqwnnjo0dP2upkhucvfvwlnt3qtrWCx0brVZ4FyuAC97gWvvzKCn/bFeLJs9xy7G9ZahFtDhoYmZ9ZB671Vo336GYWBjI4tLvgcIvztVE0zRoOweOlYPaEkQBHi9Mu6/7MfEUlh12GhsPaYpTUMzD3rDa2V/uB5wO9jpkhBb+wSzy32qvUSHmoZSiuFvl0fQjCTJAZsoYurFZeSYfGO7t3Wh6Z80bSHfqa5W/GhWlrdZw4XZ/3qV7d7WhRJGg79mNbQiURRACcF8KsCjxS40DBUI6XpWOo1WRDiQ8tws5U8iY7gDDUMZYerz0im0KoF7a2mldFxhP/dWJxL3hSWujvFHIP9yTcUhZHlrKVtxgrW1Rfi8B3oagEkmGZDCIcQY2/puFiuTYAjQA3o/hcl0bmjKRXM4LFQW80iX0lHue+oAKPeQIsAMNnfCvoRWVakYkIUcVOkfXZH9Kp9Yr0IX+67cetIfCuyCxvl672KUJQPOebQigwOtg6BbSYJQEofAU8bYn3Sh70qQl/2wZOVwuwQjzsGaIErR8+65lkKcLxSrT+34Y1TK5iQou87XSZQea/MoHtkNr8ezYwA5F01xY5KS16U5aXa81zOLZmQaJorFErrbEjjrXtDdbys6338q+fh2nFKBwm631x5ZYeMgwnBJNG/2ee+nTth1NKqNdLZ6qIdPTsEEG+ShGwYzRq22A4uDZRW3eU5R2ocdND34hf8b+MSVusBMJgcJaXz93gT84uqo7PPzXFKNfHgnYrWL5c8/BfF4wWQncOf7GmRjCExIuNt9SbKy/NlXb43cmkmFUKseWyHNZHN43/EXvjwTqQJdHd4ImBlD0ejZ7Ce6OgunysQFR3FerWW1lVv2W2gQIovJft+Q8O/y6bBvInZZmVYfblzC85KK5aIP5VIZZxxPcEH9BZ2exRSvQ6POdm+UGxzjuRwn56P61pzsj6tbJYN8cHvf6wtLBNXqBIREUKhMZtMbGq/lA3zzq3ykahURvh10nr8f5AwiUP3XuYc8Smx0M6xbnqJBcY91Du7h4BE4bDFZ8sWrC02sJKvRSAQVlG0qbGwAPpawDECFDpLu6eSeuRr1tIbXI3xEF3bsbw4i1DJm8vUirK2GPd1h6blqfsKb7/lH/UsSMzTS9+hpI+Mbvg0eJGYaTfXf4+l6MKyYNsfY5juvnuMd098lcYTaG15JUviNObz5SqwjDjhS6JGEt1mJ1RUHovKPXqsDv25Uf2e/89NIoAO1+pjFksJswk2WCO29ZZjG3O592vB/GV6VAhBYEE3L1HdD34j+B2515pkgLLFJAAAAAElFTkSuQmCC"
        action.category = "Beauty"
        list.add(action)
        action = Action()
        action.categoryImage =
            "iVBORw0KGgoAAAANSUhEUgAAACEAAAAhCAYAAABX5MJvAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAVKSURBVHgBxVhNbBtFFH4z6yQlVRtXcg8RVHFSRMgB5KgHEIhqrUIFFIkELnAiOXGjTggSl8ROcyxNnEocONW5cShsgtqG/GGXFKk9VDUUKaoozSZQBdGUuGkb8uPd4b2119ldr70Of/2k9c68fTPzzZs3b94YYAeIxCZDZrmnfyb24UBS4LMC/xA+L4VILOnnEhxnWMzXG+OxcCYreBw0SMC/AOal0DOQvCYAMqjY/0lvOAWPAmQJ+I/B3Ab1SZDUdX1sMHokBv8DinyCCKD5v9dpzR3wx+bJKm3A2ZsggJw0aPmcximpAGIs09eYgB3AZonuE8khxkA+1RtudSo+OfRddHn1cXJOz+U5ELihHjo4MZx4dTAOFcBmCc5gLKvBsFXWMR4JMiYpy6uXQ7Orb0MluHu/PoizG+r8uvu4EHo48VpcLadfdnfkCPAk5M2uXPkAKkX7c6fNoupFhNNP94mZDnwi5QjsBLU1q9aq0U+HEim5jMZyoFJUCOi3sSOZhcDinRbwQl3tHXi24VsI7L3t/BTkj/Ez+G53a8d7YlMyDgaDfeGEKUQrkKzDrN9b2w8/LB52HdScNb1favnSjYAB7K+N+nUloTMu445IWYXoiFErgdm5t2ArW1PUmGb9/FPnjHJgz69Q5duAcmCMRd1JCJ7QstBlCsgXkLds1hdwGdwI0Mxp1nW1y/DGoc+Q0Cx4g8luvuHDw0i1SYQUAiYK1S2txrW7+n23CmUvC9iwi7fhb8JGohuPZIqOdDLmyIoQzXxhuQWWVg4CBijXvsgf/iaCToGPcR6VWDV5rkEiPS8Hb680uy6BFWube6ESkE9VSRvb25ZBg1OHOwXq7894EiD8vBSCtQ1vIjd/C8HVWy8bZdJP/fhOkQ7FCVXLbu7D94LJAyoA+QrtGtqWjuBkw72HAcMa566+n2u3Vb3g1OFM10cgvxQmCS7WILA+DU2rJ413KdDMvrn+rrGDShElAkYZrZu3cNqpV3R2yBHZrwlfIW98UNUM83s+gnKgNX/6iSvQEJiz7RRy6tk5x6EnRGMm1qhaRYZPWBPYVDxFVkmZ9WrtLniBZnx94TBc/umYTV7kMwJSTgIFEpJUpUQGknJBl22fI9X6ctklcZKx4qGTBBcjbu0MEuQXEkAhpF6KT6fAYo36tc+h6f5J2Lt5Dcphbd0+6OKyxVcEjJbKuAwSlL7TiYl3iDbzg8SyndhSNeu7t25Aw4NPoTnzMVTp7ktElqAtufRHk7E0luXAfkSXcmFK+eL8ZIcpVM5PhkhWcEwigMe533qavhCRQ5KQMKdgtni/yQNws64PNFYLFSCDzhhGX0grRIChxRlPGRMUEMPvXZ73DtwtQS1HJGiV/7K7EzI1L3o1V/MEVFNAswcjWRJ+/Nbafuxouihi9gxMyT2Y8Jp13C2qxLRWIYQt95TEn+UGp9n30yBE4OyFibazX020KePjQSSgIAGKFZmsrhkWLiKR1Xxp3B0y3bzwDhLME8lcOj0TQT9p3JT2d6H/pNalAy4DozPnBqdYEMMnkyPM/JKPDYHwzetCG2l//ZUw6g77JF+03EzQR2bi6CfzJhE34D0kaD6ldCKYuZm3OHTCQo6gKEm/Mj5NB2fpC/Gp3iMRJJGCfEingBaPHbWFXLfAsz14MihJcIZ2na5BONcPGzW/t7cbqUMnlT0d00Q3WoXlWqSErl/UdT5KOUhuluv4SH7OpRAmzRm8PI0at3kOHboOiUKuUgKefw2YGOwNN6KfyLqmy5iDvAe5YJbBEzDC+C5aWxWfNOarF0k/P3BFN7C/ABaJULn/FqbBAAAAAElFTkSuQmCC"
        action.category = "Travel"
        list.add(action)

        recycler.adapter = ActionsAdapter(list)

    }

    fun addToDB(action: Action) {
        userRepository.insertAction(action)
    }

    fun viewActions() {
        val actions: ArrayList<Action> = userRepository.getActions()
        if (actions.size == 0) {
            Log.d("ACTIONS", "no actions in the db")
        } else {
            Log.d("ACTIONS", "ACTIONS----------")
            for (action: Action in userRepository.getActions()) {
                Log.d("ACTIONS", action.toString())
            }
        }
    }

    fun deleteActions() {
        userRepository.deletAllActions()
    }
}