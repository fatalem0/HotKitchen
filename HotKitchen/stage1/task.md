<h1><strong>SignIn - SingUp</strong></h1>

<h2>Description</h2>

<p>Every modern application has registration and authorization. This is done so that you can easily identify the user, build offers depending on the user&#39;s preferences, and also simply store information about who uses your application (for example, store the user&#39;s address so that he does not specify it every time). So let&#39;s add to our application the ability to register a new user.</p>

<p>In the first stage, you need to implement registration and authorization using the database. All data is stored in the database and should be preserved even if the server is restarted. You should be able to register a new user and later sign in with the same credentials.</p>

<h2>Objectives</h2>

<p>In this stage, our goal is to run a Ktor application and create several endpoints.</p>

<ul>
	<li>Create and run a Ktor application on the <code>28852</code> port.</li>
	<li>Implement the following API endpoints:</li>
</ul>

<ol>
	<li>
	<p><span style="color:#339900">POST</span> <code>/signup</code> for registration.<br />
	This method accepts an <strong><em>email</em></strong>, <strong><em>userType</em></strong> and <strong><em>password</em></strong> as JSON.<br />
	If registration was successful, response should be the next:</p>

<p><em>Response code: </em> <code>200 OK</code><br />
	<em>Response body:</em></p>

<pre>
{
    &quot;status&quot;: &quot;Signed Up&quot;
}</pre>
If registration failed, response should be the next:<br />
<br />
<em>Response code: </em><code>403 Forbidden</code><br />
<em>Response body:</em>

<pre>
<code class="language-json">{
    "status": "Registration failed"
}</code></pre>
</li>
<li><span style="color:#339900">POST</span> <code>/signin</code> for authorization.
<p>This method accepts an <strong><em>email</em></strong> and <strong><em>password</em></strong> as JSON.<br />
If authorization was successful, response should be the next:</p>

<p><em>Response code: </em><code>200 OK</code><br />
	<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Signed In"
}</code></pre>

<p>If authorization failed, response should be the next:<br />
	<br />
	<em>Response code: </em><code>403 Forbidden</code><br />
	<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Authorization failed"
}</code></pre>
</li>
</ol>

<ol>
</ol>

<p>&nbsp;</p>

<h2>Examples</h2>

<p><strong>Example 1: </strong><em>a <span style="color:#339900">POST</span> request for <code>/signup</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email": "example@domain.name",
    "userType": "staff",
    "password": "awesomepass123"
}
</code></pre>

<p><em>Response: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Signed Up"
}</code></pre>

<p>&nbsp;</p>

<p><strong>Example 2:</strong> <em>a </em>one more<em> <span style="color:#339900">POST</span> request for <code>/signup</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email": "example@domain.name",
    "userType": "staff",
    "password": "awesomepass123"
}
</code></pre>

<p><em>Response: </em><code>403 Forbidden</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Registration failed"
}</code></pre>

<p>&nbsp;</p>

<p><strong>Example 3:</strong> <em>a</em><em> <span style="color:#339900">POST</span> request for <code>/signin</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email": "example@domain.name",
    "password": "wrongpass"
}
</code></pre>

<p><em>Response: </em><code>403 Forbidden</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Authorization failed"
}</code></pre>

<p>&nbsp;</p>

<p><strong>Example 4:</strong> <em>a</em><em> <span style="color:#339900">POST</span> request for <code>/signin</code></em></p>

<p><em>Request body:</em></p>

<pre>
<code class="language-json">{
    "email": "example@domain.name",
    "password": "awesomepass123"
}
</code></pre>

<p><em>Response: </em><code>200 OK</code><br />
<em>Response body:</em></p>

<pre>
<code class="language-json">{
    "status": "Signed In"
}</code></pre>
