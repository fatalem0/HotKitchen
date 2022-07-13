<h1>Data Management</h1>

<h2>Description</h2>

<p>Great, now the staff can add dishes to the database, and all authorized users can see them. However, just looking is not enough, we also want to order. So let&#39;s implement the ability for users to place orders.</p>

<p>In this stage, you need to implement order logic.</p>

<h2>Objectives</h2>

<p>In this stage, our goal is implement creation, checking and marking orders.</p>

<ul>
	<li>First of all, we need to create a <strong>Orderl</strong> class. The Order should consist of:<br />
	<strong>orderId </strong>: <em>Integer</em> - unique id with which you can get the order<br />
	<strong>userEmail </strong>: <em>String</em> - email of the person who placed the order<br />
	<strong>mealsIds :</strong><em> Integer[] </em>- id of ordered meals<br />
	<strong>price</strong> : <em>Float - </em>total price of all meals<br />
	<strong>address</strong> : <em>String - </em>customer address<br />
	<strong>status</strong>: <em>String</em> - <em>COOK or COMPLETE</em><br />
	&nbsp;</li>
	<li>Implement the following API endpoints:</li>
</ul>

<ol>
	<li><em><span style="color:#339900">POST</span></em> <code>/order</code> for creating an order. All authorized users have access to this method.<br />
	<br />
	To make an order, the user sends an array of meal id as JSON. <em>(All statuses are <code>COOK</code> by default)</em><br />
	If the order was successfully created, the status code is <code>200 OK</code> and the response should be a fully completed <strong>order</strong>.<br />
	Otherwise the status code is <code>400 Bad Request</code><br />
	<br />
	&nbsp;</li>
	<li>
	<p><em><span style="color:#339900">POST</span></em> <code>/order/{orderId}/markReady</code> for adding changing status. Only users with <em>userType</em> <code>staff</code> can change status.<br />
	This method changes the order status to <em><code>COMPLETE</code></em><br />
	<br />
	Returns status <code>200 OK</code> if all is well.<br />
	If there is no such order, then responds with the code <code>400 Bad Request</code></p>

<p>If not the <code>staff</code> tries to mark as ready<br />
	<em>Response code: </em><code>403 Forbidden </code><br />
	<em>Response body: </em></p>

<pre>
<code class="language-json">{
  "status": "Access denied"
}</code></pre>

<p>&nbsp;</p>
	</li>
	<li>
	<p><span style="color:#3300cc"><em>GET</em></span> <code>/orderHistory </code>for getting list of all orders. All authorized users have access to this method.<br />
	<em>Response code: </em><code>200 OK </code><br />
	&nbsp;</p>
	</li>
	<li>
	<p><span style="color:#3300cc"><em>GET</em></span> <code>/orderIncomplete</code> ffor getting list of all incomplete orders. All authorized users have access to this method.<br />
	<em>Response code: </em><code>200 OK</code></p>
	</li>
</ol>

<h2><br />
Examples</h2>

<p><strong>Example 1: </strong><em>a <span style="color:#66cc66">POST</span> request for <code>/order</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">[1, 2, 1000, 9231923]</code></pre>

<p><em>Response code: </em><code>400 Bad Request</code><br />


<p><strong>&nbsp;<br />
Example 2: </strong><em>a <span style="color:#66cc66">POST</span> request for <code>/order</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">[1, 2]</code></pre>

<p><em>Response code: </em><code>400 Bad Request</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "orderId": 1144772074,
    "userEmail": "example@gmail.com",
    "mealsIds": [
        0,
        1
    ],
    "price": 43.1,
    "address": "address",
    "status": "COOK"
}</code></pre>

<p><strong>&nbsp;<br />
Example 3: </strong><em>a <span style="color:#66cc66">POST</span> request for <code>/order/{orderId}/markReady</code> as <strong>client</strong></em></p>

<p><em>Response code: </em><code>403 Forbidden</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Access denied"
}</code></pre>

<p><strong>&nbsp;<br />
Example 4: </strong><em>a <span style="color:#66cc66">POST</span> request for <code>/order/{orderId}/markReady</code> as <strong>staff</strong></em></p>

<p><em>Response code: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "orderId": 1149104263,
    "userEmail": "example@staff.com",
    "mealsIds": [1,2,3],
    "price": 66.0,
    "address": "address",
    "status": "COMPLETE"
}</code></pre>

<p><strong>&nbsp;<br />
Example 4: </strong><em>a <span style="color:#3300cc">GET</span> request for <code>/orderHisotry</code></em></p>

<p><em>Response code: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">[
    {
        "orderId": 1151045858,
        "userEmail": "example@client.com",
        "mealsIds": [
            1151044915,
            1151044916,
            1151044917
        ],
        "price": 45.0,
        "address": "address",
        "status": "COMPLETE"
    },
    {
        "orderId": 1151258563,
        "userEmail": "example@gmail.com",
        "mealsIds": [
            0,
            1
        ],
        "price": 43.1,
        "address": "newAddress",
        "status": "COOK"
    },
   
]</code></pre>

<p>&nbsp;</p>

<p><strong>Example 5: </strong><em>a <span style="color:#3300cc">GET</span> request for <code>/orderIncomplete</code></em></p>

<p><em>Response code: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">[
    {
        "orderId": 1151258563,
        "userEmail": "example@gmail.com",
        "mealsIds": [
            0,
            1
        ],
        "price": 43.1,
        "address": "newAddress",
        "status": "COOK"
    },
    {
        "orderId": 1151444488,
        "userEmail": "example@gmail.com",
        "mealsIds": [
            0
        ],
        "price": 25.8,
        "address": "address",
        "status": "COOK"
    }
]</code></pre>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p style="color:#aaaaaa">&nbsp;</p>
