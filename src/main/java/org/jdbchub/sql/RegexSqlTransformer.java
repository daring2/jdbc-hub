package org.jdbchub.sql;

import com.typesafe.config.Config;
import org.jdbchub.config.DBConfig;
import java.util.List;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.toList;
import static org.jdbchub.config.ConfigUtils.getOptString;

public class RegexSqlTransformer implements SqlTransformer {

	final Config config;
	final String urlPattern;
	final List<Rule> rules;

	public RegexSqlTransformer(Config config) {
		this.config = config;
		this.urlPattern = getOptString(config, "urlPattern").orElse(".*");
		this.rules = buildRules();
	}

	private List<Rule> buildRules() {
		Config rc = config.getConfig("rules");
		return rc.root().entrySet().stream()
			.map(ce -> new Rule(ce.getKey(), "" + ce.getValue().unwrapped()))
			.collect(toList());
	}

	@Override
	public boolean isEnabled(DBConfig dbc) {
		return dbc.url.matches(urlPattern);
	}

	@Override
	public String transform(String sql) {
		for (Rule rule : rules)
			sql = rule.apply(sql);
		return sql;
	}

	class Rule {
		final Pattern pattern;
		final String replacement;

		Rule(String regex, String replacement) {
			this.pattern = Pattern.compile(regex);
			this.replacement = replacement;
		}

		String apply(String sql) {
			return pattern.matcher(sql).replaceAll(replacement);
		}

	}

}
