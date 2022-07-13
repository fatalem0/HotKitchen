<h1><strong>Profile management</strong></h1>

<h2>Description</h2>

<p>Now we can confidently say that we are done with authorization. Now we simply can get the user&#39;s email and user type. However, this is not enough. For the full functioning of the HotKitchen, we need to create a full-fledged user profile. Let&#39;s get on with it!</p>

<p>In this stage, you need to implement the user profile logic with receiving, adding, updating and deleting user information.</p>

<h2>Objectives</h2>

<p>In this stage, our goal is create user profile and create methods to manage it.</p>

<ul>
	<li>Implement the following API endpoints:</li>
</ul>

<ol>
	<li><span style="color:#3300cc">GET</span> <code>/me</code> for full information about the user.<br />
	Full information about the user is <strong><em>id</em></strong>, <strong><em>name</em></strong>, <strong><em>userType</em></strong>, <strong><em>phone</em></strong>, <strong><em>email</em></strong>, <strong><em>address. </em></strong>

<p><em>Response code: </em><code>200 OK</code><br />
	<em>Response body: </em></p>

<pre>
<code class="language-json">{
    "name": "Goose",
    "userType": "client",
    "phone": "+79999999999",
    "email": "example@gmail.com",
    "address": "address"
}</code></pre>

<p>If there is no such user, then you need to return the code <code>400 Bad Request</code><br />
	<em>Response code: </em><code>400 Bad Request</code></p>

<p>&nbsp;</p>
	</li>
	<li><span style="color:#ff3300">PUT</span>&nbsp;<code>/me</code>&nbsp; for updating user information.<br />
	This method receives full information about the user and updates the data with it. If there is no information about the user yet, then it creates a new one with the specified data.<br />
	<em>Response code: </em><code>200 OK</code><br />
	<em>Response body: </em>
	<pre>
<code class="language-json">{
    "name":"newName",
    "userType":"newType",
    "phone":"+79999999999",
    "email":"example@gmail.com",
    "address":"newAddress"
}</code></pre>

<blockquote>
	<p>It is important that you cannot change the email, otherwise you need to respond with code 400 Bad Request.</p>
	</blockquote>
	</li>
	<br />
	<li>
	<p><span style="color:#990000">DELETE</span> <code>/me</code>&nbsp; for deleting user information.<br />
	This method removes all information about the user, <strong>including authorization data</strong>.<br />
	If the deletion was successful, return code <code>200 OK</code><br />
	<em>Response code: </em><code>200 OK</code><br />
	<br />
	<em>Else return code </em><code>400 Bad Request</code><br />
	<em>Response code: </em><code>400 Bad Request </code></p>

<blockquote>Notice:<br />
	You need to use Bearer authorization to access each method. After all, only with a token user can be identified.</blockquote>
	</li>
</ol>

<h2>Examples</h2>

<blockquote>
<p>Notice:<br />
All requests using a valid JWT token received by the user with email example@gmail.com</p>
</blockquote>

<p><strong>Example 1: </strong><em>a <span style="color:#3300cc">GET</span> request for <code>/me</code></em></p>

<p><em>Response code: </em><code>400 Bad Request</code></p>

<p><br />
<strong>Example 2: </strong><em>a <span style="color:#ff3300">PUT</span> request for <code>/me</code></em></p>

<p><em>&nbsp;Request body:</em></p>

<pre>
<code class="language-json">{
    "name":"Goose",
    "userType":"client",
    "phone":"+79999999999",
    "email":"example@gmail.com",
    "address":"address"
}</code></pre>

<p><em>Response code: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "name":"Goose",
    "userType":"client",
    "phone":"+79999999999",
    "email":"example@gmail.com",
    "address":"address"
}</code></pre>

<p><br />
<strong>Example 3: </strong><em>a <span style="color:#3300cc">GET</span> request for <code>/me</code></em></p>

<p><em>Response code: </em><code>200 OK </code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "name":"Goose",
    "userType":"client",
    "phone":"+79999999999",
    "email":"example@gmail.com",
    "address":"address"
}</code></pre>

<p><strong>&nbsp;<br />
Example 4: </strong><em>a <span style="color:#ff3300">PUT</span> request for <code>/me</code></em></p>

<p><em>&nbsp;Request body:</em></p>

<pre>
<code class="language-json">{
    "name":"newName",
    "userType":"newType",
    "phone":"+79999999999",
    "email":"example@gmail.com",
    "address":"newAddress"
}</code></pre>

<p><em>Response code: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "name":"newName",
    "userType":"newType",
    "phone":"+79999999999",
    "email":"example@gmail.com",
    "address":"newAddress"
}</code></pre>

<p><strong>&nbsp;<br />
Example 5: </strong><em>a <span style="color:#3300cc">GET</span> request for <code>/me</code></em></p>

<p><em>Response code: </em><code>200 OK </code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "name":"newName",
    "userType":"newType",
    "phone":"+79999999999",
    "email":"example@gmail.com",
    "address":"newAddress"
}</code></pre>

<p><br />
<strong>Example 6: </strong><em>a <span style="color:#ff3300">PUT</span> request for <code>/me</code></em></p>

<p><em>&nbsp;Request body:</em></p>

<pre>
<code class="language-json">{
    "name":"newName",
    "userType":"newType",
    "phone":"+79999999999",
    "email":"NEWEMAIL@gmail.com",
    "address":"newAddress"
}</code></pre>

<p><em>Response code: </em><code>400 Bad Request</code></p>

<p><strong>Example 7: </strong><em>a <span style="color:#990000">DELETE</span> request for <code>/me</code></em></p>

<p><em>Response code: </em><code>200 OK </code></p>

<p><br />
<strong>Example 8: </strong><em>a <span style="color:#990000">DELETE</span> request for <code>/me</code></em></p>

<p><em>Response code: </em><code>400 Bad Request</code></p>
