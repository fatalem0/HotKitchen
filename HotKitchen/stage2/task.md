<h1><strong>testdata.Token usage</strong></h1>

<h2>Description</h2>

<p>Now we have learned how to log in and register a user using an email address and a password. However, it is terribly inconvenient every time we want to access resources to pass email and password for authentication. Moreover, storing a password is very insecure.</p>

<p>In this stage, you need to implement authorization by Bearer JWT token, implement password and email validation and update auth statuses.</p>

<h2>Objectives</h2>

<p>In this stage, our goal is update existing methods and improve the authorization logic.</p>

<ul>
	<li>Update the following API endpoints:<br />
	&nbsp;</li>
</ul>

<ol>
	<li><span style="color:#339900">POST</span> <code>/signup</code> now should return JWT token and corresponding statuses:

<p>&nbsp;1) If the registration was successful:</p>

<p><em>Response code: </em><code>200 OK</code><br />
	<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "token": "&lt;JWT token&gt;"
}</code></pre>

<p><br />
	2) If <em>email </em>was invalid:</p>

<p><em>Response code: </em><code>403 Forbidden</code><br />
	<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Invalid email"
}</code></pre>

<blockquote>
	<p>Notice:<br />
	A valid email address consists of an email prefix and an email domain, both in acceptable formats<br />
	(only letters, numbers and special symbols can be used).<br />
	For example:<br />
	example@mail.com - valid email<br />
	123.wrong.email - invalid email</p>
	</blockquote>

<p><br />
	3) If <em>password</em> was invalid:</p>

<p><em>Response code: </em><code>403 Forbidden</code><br />
	<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Invalid password"
}</code></pre>

<blockquote>
	<p>Notice:<br />
	A valid password is a password that is at least 6 characters long and consists of letters and numbers<br />
	For example:<br />
	Example1234 - valid password<br />
	1234 - invalid password</p>
	</blockquote>

<p><br />
	4) If <em>user already exists</em>:</p>

<p><em>Response code: </em><code>403 Forbidden</code><br />
	<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "User already exists"
}</code></pre>

<p><br />
	&nbsp;</p>
	</li>
	<li><span style="color:#339900">POST</span> <code>/signin</code> now should return JWT token and corresponding statuses:
	<p>&nbsp;1) If the authorization was successful:</p>

<p><em>Response code: </em><code>200 OK</code><br />
	<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "token": "&lt;JWT token&gt;"
}</code></pre>

<p><br />
	2) If authorization failed:</p>

<p><em>Response code: </em><code>403 Forbidden</code><br />
	<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Invalid email or password"
}</code></pre>

<p>&nbsp;</p>
	</li>
</ol>

<ul>
	<li>
	<p>Implement the following API endpoint:</p>
	</li>
</ul>

<ol>
	<li>&nbsp;<span style="color:#3300cc">GET</span> <code>/validate</code> for token validation. This method checks if token is valid.<br />
	<br />
	1) If token is valid:<br />
	<em>Response code: </em><code>200 OK</code><br />
	<em>Response body: </em>

<pre>
<code class="language-json">Hello, &lt;userType&gt; &lt;email&gt;</code></pre>
Where <code>&lt;userType&gt;</code> <code>&lt;email&gt;</code> it is <em>userType </em>and<em> email</em> of the user in whose name the token was received.<br /><br />2) if token is invalid:<br />
<em>Response code: </em><code>401 Unauthorized </code>

<blockquote>Notice:<br />
	All authorization must be done using a Bearer token in headers.</blockquote>
	</li>
</ol>

<h2>&nbsp;</h2>

<h2>Examples</h2>

<p><strong>Example 1: </strong><em>a <span style="color:#339900">POST</span> request for <code>/signin</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email":"example.gmail.com",
    "userType":"client",
    "password":"password123"
}</code></pre>

<p><br />
<em>Response code: </em><code>403 Forbidden</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Invalid email"
}</code></pre>

<p>&nbsp;</p>

<p><strong>Example 2:</strong> <em>a&nbsp;</em><em> <span style="color:#339900">POST</span> request for <code>/signin</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email":"example@gmail.com",
    "userType":"client",
    "password":"password"
}</code></pre>

<p><br />
<em>Response code: </em><code>403 Forbidden</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Invalid password"
}</code></pre>

<p>&nbsp;</p>

<p><strong>Example 3:</strong> <em>a</em><em> <span style="color:#339900">POST</span> request for <code>/signin</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email":"example@gmail.com",
    "userType":"client",
    "password":"password123"
}</code></pre>

<p><br />
<em>Response code: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyVHlwZSI6ImNsaWVudCIsImV4cCI6MTY0NjA4NDk2NSwiZW1haWwiOiJleGFtcGxlQGdtYWlsLmNvbSJ9.W8maMBRlbI0z3Qqtv5ONUmRsP6ZlWooWBje7IuwhLUQ"
}</code></pre>

<p>&nbsp;</p>

<p><strong>Example 4:</strong> <em>a </em><em>one more <span style="color:#339900">POST</span> request for <code>/signin</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email":"example@gmail.com",
    "userType":"client",
    "password":"password123"
}</code></pre>

<p><br />
<em>Response code: </em><code>403 Forbidden</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "User already exists"
}</code></pre>

<p>&nbsp;</p>

<p><strong>Example 5:</strong> <em>a</em><em> <span style="color:#339900">POST</span> request for <code>/signup</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email":"stranger@gmail.com",
    "password":"password123"
}</code></pre>

<p><em>Response code: </em><code>403 Forbidden</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Invalid email or password"
}</code></pre>

<p>&nbsp;</p>

<p><strong>Example 6:</strong> <em>a</em><em> <span style="color:#339900">POST</span> request for <code>/signup</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email":"example@gmail.com",
    "password":"wrongPassword"
}</code></pre>

<p><em>Response code: </em><code>403 Forbidden</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Invalid email or password"
}</code></pre>

<p>&nbsp;</p>

<p><strong>Example 7:</strong> <em>a</em><em> <span style="color:#339900">POST</span> request for <code>/signup</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email":"example@gmail.com",
    "password":"password123"
}</code></pre>

<p><em>Response code: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyVHlwZSI6ImNsaWVudCIsImV4cCI6MTY0NjA4NTI2MSwiZW1haWwiOiJleGFtcGxlQGdtYWlsLmNvbSJ9.kcxz3UtghuJpOdRT-cvJRGWbBLRvhSqvGZjROVvchFY"
}</code></pre>

<p><br />
<br />
<strong>Example 8:</strong> <em>a</em><em> <span style="color:#3300cc">GET</span><span style="color:#66cc66"> </span>request for <code>/validate</code></em></p>

<p><em>Request headers:</em></p>

<pre>
<code class="language-json">Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyVHlwZSI6Im1lbSIsImV4cCI6MTY0NjA4MzkzNSwiZW1haWwiOiJtZW1AZ21haWwuY29tIn0.7KSaeH2khQbsH9AYF1Wr9NzjtnXNd2Ki5X1fWNsVGTY</code></pre>

<p><em>Response code: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">Hello, client example@gmail.com</code></pre>

<p>&nbsp;</p>

<p><strong>Example 8:</strong> <em>a</em><em> <span style="color:#3300cc">GET</span><span style="color:#66cc66"> </span>request for <code>/validate</code></em></p>

<p><em>Request headers:</em></p>

<pre>
<code class="language-json">Authorization: Bearer something.very.strange</code></pre>

<p><em>Response code: </em><code>401 Unauthorized </code></p>
