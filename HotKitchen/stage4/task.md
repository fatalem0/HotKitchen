<h1><strong>Access Rights</strong></h1>

<h2>Description</h2>

<p>Finally, we have a completely ready interaction with the user. We have implemented registration and authorization, access by token, as well as a full-fledged user profile. Now it&#39;s time to move on and add functionality to our HotKitchen.</p>

<p>In this stage, you need to implement addition and receiving meals using different access rights.</p>

<h2>Objectives</h2>

<p>In this stage, our goal is implement interaction of different types of users with meals and categories of meals.</p>

<ul>
	<li>First of all, we need to create a <strong>Meal</strong> class. The Meal should consist of:<br />
	<strong>mealId </strong>: <em>Integer</em> -&nbsp; unique id with which you can get the meal<br />
	<strong>title</strong> : <em>String - </em>short meal name<br />
	<strong>price</strong> : <em>Float - </em>meal price<br />
	<strong>imageUrl </strong>: <em>String - </em>url meal image preview<br />
	<strong>categoryIds </strong>: <em>Integer[]</em> - <em>array of </em>categories to which the meal belongs<br ><br >
<p>Also we need to create <strong>Category</strong> class. The Category should consist of:<br />
	<strong>categoryId </strong>: <em>Integer</em> -&nbsp; unique id with which you can get the category<br />
	<strong>title</strong> : <em>String - </em>short category name<br />
	<strong>description: </strong><em>String</em> - full description of this category</p>
	</li><br ><br >
	<li>Implement the following API endpoints:<br />
	&nbsp;</li>
</ul>

<ol>
	<li><em><span style="color:#339900">POST</span></em> <code>/addMeal</code> for adding a meal. Only users with <em>userType</em> <code>staff</code> can add meals.<br />
	<br />
	<em>1) </em>If the addition was successful<br />
	<em>Response code: </em><code>200 OK</code>
<br ><br >
<p><em>2) </em>If the addition failed<br />
	<em>Response code: </em><code>400 Bad Request </code><br />
	<br />
	3) If not the <code>staff</code> tries to add the meal<br />
	<em>Response code: </em><code>403 Forbidden </code><br />
	<em>Response body: </em></p>

<pre>
<code class="language-json">{
  "status": "Access denied"
}</code></pre>

<p>&nbsp;</p>
	</li>
	<li>
	<p><em><span style="color:#339900">&nbsp;POST</span></em> <code>/addCategory</code> for adding a category. Only users with <em>userType</em> <code>staff</code> can add categories.<br />
	<br />
	<em>1) </em>If the addition was successful<br />
	<em>Response code: </em><code>200 OK</code></p>
<br ><br >
<p><em>2) </em>If the addition failed<br />
	<em>Response code: </em><code>400 Bad Request </code><br />
	<br />
	3) If not the <code>staff</code> tries to add the category<br />
	<em>Response code: </em><code>403 Forbidden </code><br />
	<em>Response body: </em></p>

<pre>
<code class="language-json">{
  "status": "Access denied"
}</code></pre>

<p>&nbsp;</p>
	</li>
	<li>
	<p><span style="color:#3300cc"><em>GET</em></span> <code>/meals</code> for getting list of all meals. All authorized users have access to this method.<br />
	<em>Response code: </em><code>200 OK</code><br />
	&nbsp;</p>
	</li>
	<li>
	<p><span style="color:#3300cc"><em>GET</em></span> <code>/meals/{id}</code> for&nbsp;to getting one meal by <code>id</code>. All authorized users have access to this method.<br />
	<em>Response code: </em><code>200 OK</code><br />
	&nbsp;</p>
	</li>
	<li>
	<p><span style="color:#3300cc"><em>GET</em></span> <code>/categories</code> for getting list of all categories. All authorized users have access to this method.<br />
	<em>Response code: </em><code>200 OK</code><br />
	&nbsp;</p>
	</li>
	<li>
	<p><span style="color:#3300cc"><em>GET</em></span> <code>/categories/{id}</code> for&nbsp;to getting one meal by <code>id</code>. All authorized users have access to this method.<br />
	<em>Response code: </em><code>200 OK</code></p>
	</li>
</ol>

<h2><br />
Examples</h2>

<p><strong>&nbsp;Example 1: </strong><em>a <span style="color:#3300cc">GET</span> request for <code>/meals</code></em></p>

<p><em>Response code: </em><code>200 OK </code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">[]</code></pre>

<p><br />
<strong>Example 2: </strong><em>a <span style="color:#66cc66">POST</span> request for <code>/addMeal</code></em></p>

<p><em>&nbsp;Request body:</em></p>

<pre>
<code class="language-json">{
    "mealId": 0,
    "title": "Soup",
    "price": 25.8,
    "imageUrl": "https://www.inspiredtaste.net/wp-content/uploads/2018/09/Easy-Chicken-Noodle-Soup-Recipe-1200jpg",
    "categoryIds": [1,5]
}</code></pre>

<p><em>Response code: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "mealId": 0,
    "title": "Soup",
    "price": 25.8,
    "imageUrl": "https://www.inspiredtaste.net/wp-content/uploads/2018/09/Easy-Chicken-Noodle-Soup-Recipe-1200jpg",
    "categoryIds": [1,5]
}</code></pre>

<p>&nbsp;</p>

<p><strong>Example 3: </strong><em>a <span style="color:#66cc66">POST</span> request for <code>/addMeal</code></em></p>

<p><em>&nbsp;Request body:</em></p>

<pre>
<code class="language-json">{
    "mealId": 0,
    "title": "Soup",
    "price": 25.8,
    "imageUrl": "https://www.inspiredtaste.net/wp-content/uploads/2018/09/Easy-Chicken-Noodle-Soup-Recipe-1200jpg",
    "categoryIds": [1,5]
}</code></pre>

<p><em>Response code: </em><code>400 Bad Request </code></p>

<p><strong>&nbsp; </strong><br />
<strong>Example 4: </strong><em>a <span style="color:#3300cc">GET</span> request for <code>/meals</code></em></p>

<p><em>Response code: </em><code>200 OK </code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">[
    {
        "mealId": 0,
        "title": "Soup",
        "price": 25.8,
        "imageUrl": "https://www.inspiredtaste.net/wp-content/uploads/2018/09/Easy-Chicken-Noodle-Soup-Recipe-1200jpg",
        "categoryIds": [
            1,
            5
        ]
    },
    {
        "mealId": 1,
        "title": "Pies",
        "price": 17.3,
        "imageUrl": "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/best-pie-recipes-1635795947.jpeg",
        "categoryIds": [
            3,
            6,
            7
        ]
    }
]</code></pre>

<p><br />
<strong>Example 5: </strong><em>a <span style="color:#3300cc">GET</span> request for <code>/meals/0</code><br />
Response code: </em><code>200 OK </code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "mealId": 0,
    "title": "Soup",
    "price": 25.8,
    "imageUrl": "https://www.inspiredtaste.net/wp-content/uploads/2018/09/Easy-Chicken-Noodle-Soup-Recipe-1200jpg",
    "categoryIds": [
        1,
        5
    ]
}</code></pre>

<p><br />
<strong>Example 6: </strong><em>a <span style="color:#3300cc">GET</span> request for <code>/meals/12</code><br />
Response code: </em><code>400 Bad Request </code></p>

<p><strong>&nbsp;<br />
Example 7: </strong><em>a <span style="color:#66cc66">POST</span> request for <code>/addCategory</code></em></p>

<p><em>&nbsp;Request body:</em></p>

<pre>
<code class="language-json">{
    "categoryId": 0,
    "title": "Hot drinks",
    "description": "Drinks are liquids specifically prepared for human consumption."
}</code></pre>

<p><em>Response code: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "categoryId": 0,
    "title": "Hot drinks",
    "description": "Drinks are liquids specifically prepared for human consumption."
}</code></pre>
